package com.example.demo.controller;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 上午8:36:55
 */

@Component
@RefreshScope
@Data
@ToString
@ConfigurationProperties(prefix = "appiron.emm.client")
public class ClientCertificationConfig {
	List<ClientCertification> client_certifications;
}
