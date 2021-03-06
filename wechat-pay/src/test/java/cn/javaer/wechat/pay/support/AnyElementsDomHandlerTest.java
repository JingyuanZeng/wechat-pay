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

import cn.javaer.wechat.pay.model.OrderQueryResponse;
import cn.javaer.wechat.pay.model.base.Coupon;
import cn.javaer.wechat.pay.util.CodecUtils;
import org.junit.Test;

import java.util.List;

import static cn.javaer.wechat.test.Assertions.assertThat;

/**
 * @author cn-src
 */
public class AnyElementsDomHandlerTest {

    @Test
    public void elementsToMap() {

        final OrderQueryResponse response = CodecUtils.unmarshal(
                "<xml><sign>d</sign><nonce_str>nonce_str_value</nonce_str><coupon_type_0>CASH</coupon_type_0></xml>",
                OrderQueryResponse.class);

        final List<Coupon> coupons = response.getCoupons();
        assertThat(coupons)
                .hasSize(1);
        assertThat(coupons.get(0)).hasType(Coupon.Type.CASH);
    }
}