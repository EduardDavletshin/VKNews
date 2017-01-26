package com.example.eddy.vknews;

import com.example.eddy.vknews.Models.ResponseWrapper;

interface Callback {
    void onFinish(ResponseWrapper callbackResponse);
}
