package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月1日 下午1:18:46
 */

@Component
@Data
@ToString
public class ClientCertification {
	String package_name;
	List<String> ip_list;
	String time_scope_start;
	String time_scope_end;
	String cert_token;
}
