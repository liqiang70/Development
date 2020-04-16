package com.example.demo.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年2月25日 下午7:08:30
 */

@RestController
@RequestMapping
public class PrometheusController {

	@Autowired
	private CollectorRegistry collectorRegistry;

	// 这里produces必须这样设置才能被Prometheus解析
	@GetMapping(value = "/metrics", produces = "text/plain; version=0.0.4; charset=utf-8")
	public String data() {
		try {
			Writer writer = new StringWriter();
			TextFormat.write004(writer, collectorRegistry.metricFamilySamples());

			return writer.toString();
		} catch (IOException ex) {
			// This actually never happens since StringWriter::write() doesn't throw any
			// IOException
			throw new RuntimeException("Writing metrics failed", ex);
		}
	}
}
