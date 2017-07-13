package org.service;

import java.util.List;

public interface NVRService {
	/**
	 * 获取NVR信息,需要修改，因为dao里面调用了dao，业务逻辑有问题，建议提到service中，以传参的形式来代替
	 * @return
	 */
	public List getNVRInfo();
}
