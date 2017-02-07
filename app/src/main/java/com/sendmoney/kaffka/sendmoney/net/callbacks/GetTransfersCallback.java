package com.sendmoney.kaffka.sendmoney.net.callbacks;

import com.sendmoney.kaffka.sendmoney.models.Transfer;

import java.util.List;

/**
 * Created by kaffka on 04/02/2017.
 */

public interface GetTransfersCallback {
    void onGetTransfersSuccess(List<Transfer> transfers);
    void onGetTransfersError();
}
