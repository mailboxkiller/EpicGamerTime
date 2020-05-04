package dev.trainwreck.computermod.common.computer.javascript;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


public class JavaScriptWriter extends Writer {
    private ArrayList<String> arrayList;

    public JavaScriptWriter(){
        arrayList = new ArrayList<>();
        lock = arrayList;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cbuf, off, len);
        if(stringBuffer.toString().equals("\r\n")) return;
        arrayList.add(stringBuffer.toString());
    }

    public String getString(int index){
        return arrayList.get(index);
    }

    public ArrayList<String> getOutputs() {
        return arrayList;
    }

    public void clear(){
        arrayList.clear();
    }
    @Override
    public void flush() {
    }

    @Override
    public void close() {

    }
}
