package com.example.demoreporunittestci

/**
 * @author mvn-toan.nguyen2 on 7/26/23
 **/
class MainViewModel {
    private var count = 0;

    internal fun getCount() = count

    internal fun setCount(data: Int) {
        count = data
    }

}
