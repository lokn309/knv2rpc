package cn.lokn.knv2rpc.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: lokn
 * @date: 2024/03/31 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest {

    private String service;
    private String method;
    private Object[] args;

}
