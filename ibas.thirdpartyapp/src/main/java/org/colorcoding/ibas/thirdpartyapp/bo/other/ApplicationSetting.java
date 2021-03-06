package org.colorcoding.ibas.thirdpartyapp.bo.other;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.serialization.Serializable;
import org.colorcoding.ibas.thirdpartyapp.MyConfiguration;
import org.colorcoding.ibas.thirdpartyapp.data.DataConvert;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ApplicationSetting", namespace = MyConfiguration.NAMESPACE_BO)
@XmlRootElement(name = "ApplicationSetting", namespace = MyConfiguration.NAMESPACE_BO)
public class ApplicationSetting extends Serializable {

	private static final long serialVersionUID = 4904485815664786742L;

	@XmlElement(name = "Name")
	private String name;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Group")
	private String group;

	public final String getGroup() {
		return group;
	}

	public final void setGroup(String group) {
		this.group = group;
	}

	@XmlElement(name = "Description")
	private String description;

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	private String secretKey;

	public final String getSecretKey() {
		return secretKey;
	}

	public final void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	private ApplicationSettingItems settingItems;

	@XmlElementWrapper(name = "SettingItems")
	@XmlElement(name = "ApplicationSettingItem", type = ApplicationSettingItem.class)
	public final ApplicationSettingItems getSettingItems() {
		if (this.settingItems == null) {
			this.settingItems = new ApplicationSettingItems(this);
		}
		return settingItems;
	}

	@SuppressWarnings("unchecked")
	public final <T> T paramValue(String name, T defaultValue) {
		String value = this.paramValue(name);
		if (value == null) {
			return defaultValue;
		}
		if (defaultValue == null) {
			return (T) value;
		} else {
			return (T) DataConvert.convert(defaultValue.getClass(), value);
		}
	}

	public final String paramValue(String name) {
		String value = null;
		for (ApplicationSettingItem item : this.getSettingItems()) {
			if (item.getName() == null) {
				continue;
			}
			if (!item.getName().equalsIgnoreCase(name)) {
				continue;
			}
			value = item.getValue();
			break;
		}
		return value;
	}

	@Override
	public String toString() {
		return String.format("{applicationSetting: %s}", this.getName());
	}

}
