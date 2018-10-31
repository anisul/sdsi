package types;

import util.AppUtil;

public class Bin {
    public int[] address = new int[AppUtil.lengthOfData];
    public int[] data = new int[AppUtil.lengthOfData];

    public Bin() {
        this.address = AppUtil.generateBinaryRandomAddress(AppUtil.lengthOfData);
    }

    public int[] getAddress() {
        return address;
    }

    public void setAddress(int[] address) {
        this.address = address;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
