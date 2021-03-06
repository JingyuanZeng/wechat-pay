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

package cn.javaer.wechat.pay.util;

import cn.javaer.wechat.pay.model.OrderQueryRequest;
import cn.javaer.wechat.pay.model.OrderQueryResponse;
import cn.javaer.wechat.pay.model.base.SignType;
import cn.javaer.wechat.pay.model.base.TradeState;
import org.junit.Test;

import static cn.javaer.wechat.test.Assertions.assertThat;


/**
 * @author cn-src
 */
public class CodecUtilsTest {

    @Test
    public void unmarshal() {
        final OrderQueryResponse response = CodecUtils.unmarshal("<xml><openid>openid</openid><trade_state>SUCCESS</trade_state></xml>", OrderQueryResponse.class);
        assertThat(response).hasTradeState(TradeState.SUCCESS);
    }

    @Test
    public void unmarshalUnknown() {
        final OrderQueryResponse response = CodecUtils.unmarshal("<xml><openid>openid</openid><trade_state>unknown</trade_state></xml>", OrderQueryResponse.class);
        assertThat(response).hasTradeState(null);
        assertThat(response.getOtherParams()).containsKey("trade_state").containsValue("unknown");
        final String sign = SignUtils.generateSign(response, "key");
        assertThat(sign).isEqualTo("0EDD82E066154AD999BEAD0A90A4F205");
    }

    @Test
    public void marshal() {
        final OrderQueryRequest request = new OrderQueryRequest();
        request.setSignType(SignType.HMAC_SHA256);
        assertThat(CodecUtils.marshal(request)).contains("<sign_type>HMAC-SHA256</sign_type>");
    }
}