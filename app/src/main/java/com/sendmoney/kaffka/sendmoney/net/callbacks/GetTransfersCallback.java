package com.sendmoney.kaffka.sendmoney.net.callbacks;

import com.sendmoney.kaffka.sendmoney.models.Transfer;

import java.util.List;

/**
 * Created by gabri on 04/02/2017.
 */

public interface GetTransfersCallback {
    void onGetTrasfersSuccess(List<Transfer> transfers);
    void onGetTransfersError();
}
