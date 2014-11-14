package com.kkk.rest.exception;


public class KSystemException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KErrorCode error = null;
    private boolean suppressTrace = false;
    private static final String ERROR_DETAIL_SEPARATOR = ":_KKK_:";
    private static final String UNKNOWN_ERROR = "Unknown Error";
    private String localizationKey = null;
    private String code;
    private String displayMessage;

    public KSystemException() {
        super();
    }

    public KSystemException(String message) {
        super(message);
    }

    public KSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public KSystemException(KErrorCode error, String message, boolean suppressStackTrace) {
        this(error, message);
        suppressStackTrace(suppressStackTrace);
    }

    public KSystemException(Throwable cause) {
        super(cause);
    }

    public KSystemException(KErrorCode error) {
        super();
        setError(error);
    }

    public KSystemException(KErrorCode error, String message) {
        this(message);
        setError(error);
    }

    public KSystemException(KErrorCode error, String message, Throwable cause) {
        this(message, cause);
        setError(error);
    }

    public KSystemException(KErrorCode error, Throwable cause) {
        this(cause);
        setError(error);
    }

    public String getMessage() {
        String message = super.getMessage();
        return message != null ? message : error.getMessage();
    }

    public String getDisplayMessage() {
        return displayMessage == null ? getMessage() : displayMessage;
    }

    private void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getCode() {
        if (code != null) {
            return code;
        }
        return error != null ? error.getCode() : KErrorCode.UNKNOWN_ERROR.getCode();
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getLocalizationKey() {
        return localizationKey;
    }

    public void setLocalizationKey(String localizationKey) {
        this.localizationKey = localizationKey;
    }

    public void setError(KErrorCode error) {
        this.error = error;
        this.localizationKey = error.getLocalizationKey();
    }

    public boolean isStackTraceSuppressed() {
        return suppressTrace;
    }

    public void suppressStackTrace(boolean tf) {
        this.suppressTrace = tf;
    }

    /**
     * Client-side exception interceptor will need to parse HTTP ERROR_DETAIL to obtain
     * the message and localization key, since the KKKErrorCode is not available.
     *
     * @param errorDetailString HTTP ERROR_DETAIL
     * @return a KKKSystemException with message/localizationKey parsed from the passed string
     */
    public static KSystemException fromErrorDetail(String errorDetailString) {
        return parseErrorDetailString(errorDetailString);
    }


    /**
     * Server side Exception Mapper will need a string to use as HTTP ERROR_DETAIL.   This method
     * is used to produce that string.
     * <p/>
     * the exception string will be of the form: "_KKK_:<errorCode>:<exceptionText>:<localizationKey>"
     * <p/>
     * where errorCode is the KKKErrorCode string
     * exceptionText is the exception message
     * localizationKey is a possibly-empty UI Resource label key
     *
     * @return a string with special delimiters that will be removed on client side
     */
    public String getHTTPErrorDetail() {
        return getCode() + ERROR_DETAIL_SEPARATOR + getMessage() + ERROR_DETAIL_SEPARATOR +
                (getLocalizationKey() == null ? "" : getLocalizationKey());
    }

    /**
     * Server-side throw will use the string produced above in the ERROR_DETAIL of the HTTP response
     * The client-side interceptor can use this method to extract the localization key, which if present
     * can be used to look up a UI label resource
     *
     * @param errorDetailString the ERROR_DETAIL of the HTTP response
     * @return array of 2 Strings:
     *         result[0] = error string without localization key
     *         result[1] = localization key if present, else null
     */
    private static KSystemException parseErrorDetailString(String errorDetailString) {
        String msg = errorDetailString;
        String code = null;
        String localizationKey = null;
        String displayMessage = null;
        int index = msg.indexOf(" at [");
        if (index > 0) {
            msg = msg.substring(0, index);
            index = msg.indexOf(":");
            if (index > 0) {
                msg = msg.substring(index + 1, msg.length()).trim();
            }
        }

        if (msg.contains(ERROR_DETAIL_SEPARATOR)) {
            String[] splits = msg.split(ERROR_DETAIL_SEPARATOR);
            if (splits.length < 2) {
                localizationKey = "unknownError";
                msg = splits[0] == null || splits[0].trim().isEmpty() ? UNKNOWN_ERROR : splits[0];
            } else {
                code = splits[0] == null ? null : splits[0];
                msg = code;
                if (splits[1] != null) {
                    msg = code == null ? splits[1] : code + " : " + splits[1];
                }
                if (msg == null) {
                    msg = UNKNOWN_ERROR;
                }

                if (splits[1] != null && !splits[1].trim().isEmpty()) {
                    displayMessage = splits[1].trim();
                }
                if (splits.length == 3) {
                    String key = splits[2];
                    if (key != null && !key.trim().isEmpty()) {
                        localizationKey = key;
                    }
                }
            }
        }

        KSystemException ex = new KSystemException(msg);
        ex.setCode(code);
        ex.setLocalizationKey(localizationKey);
        ex.setDisplayMessage(displayMessage);
        return ex;
    }
}