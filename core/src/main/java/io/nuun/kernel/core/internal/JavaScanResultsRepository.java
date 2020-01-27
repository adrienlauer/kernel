package io.nuun.kernel.core.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class JavaScanResultsRepository implements ScanResultsRepository {
    @Override
    public ScanResults load(InputStream is) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(is);
        try {
            return (ScanResults) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void save(ScanResults scanResults, OutputStream os) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(scanResults);
    }
}
