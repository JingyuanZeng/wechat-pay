/*
 * Copyright (c) 2018 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.javaer.wechat.pay.support;

import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 微信支付返回的是 MediaType.TEXT_PLAIN, 故此处增加支持.
 *
 * @author zhangpeng
 */
public class WeChatPayJaxb2RootElementHttpMessageConverter extends Jaxb2RootElementHttpMessageConverter {
    public WeChatPayJaxb2RootElementHttpMessageConverter() {
        final List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(Collections.unmodifiableList(mediaTypes));
    }
}