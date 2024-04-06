package cn.lokn.knv2rpc.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: lokn
 * @date: 2024/03/31 20:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse<T> {

    private boolean status; // true-成功；
    private T data;

}
