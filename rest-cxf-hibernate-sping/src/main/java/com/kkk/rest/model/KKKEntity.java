package com.kkk.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.bind.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class KKKEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(name = "create-time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(name = "update-time")
    private Date updateTime;

    @Transient
    @XmlTransient
    public abstract Long getId();

    public abstract void setId(Long id);

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
