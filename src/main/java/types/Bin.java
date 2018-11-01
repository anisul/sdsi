package types;

import util.AppUtil;

public class Bin {
    public int[] address = new int[AppUtil.lengthOfData];
    public int[] data = new int[AppUtil.lengthOfData];

    public Bin(int nodeId, int binId) {
        this.address = AppUtil.generateBinaryRandomAddress(AppUtil.lengthOfData, nodeId, binId, true);
        this.data = AppUtil.generateBinaryRandomAddress(AppUtil.lengthOfData, nodeId, binId, false);
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

    public StringBuffer printBin() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            sb.append(this.address[i]);
        }
        sb.append(" --- ");

        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            sb.append(this.data[i]);
        }
        sb.append("\n");
        return sb;
    }

    public void printBinInConsole() {
        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            System.out.print(this.address[i]);
        }
        System.out.print(" --- ");

        for (int i = 0; i < AppUtil.lengthOfData; i++) {
            System.out.print(this.data[i] + " ");
        }

        System.out.println("");
    }
}
