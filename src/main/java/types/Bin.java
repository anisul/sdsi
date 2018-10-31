package types;

import util.AppUtil;

public class Bin {
    public int[] address = new int[AppUtil.lengthOfData];
    public int[] data = new int[AppUtil.lengthOfData];

    public Bin() {
        this.address = AppUtil.generateBinaryRandomAddress(AppUtil.lengthOfData);
        //printBin();
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

    public void printBin() {
        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            System.out.print(this.address[i]);
        }
        System.out.print("---");

        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            System.out.print(this.data[i]);
        }

        System.out.println("");
    }
}
