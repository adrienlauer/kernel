package io.nuun.kernel.core.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ScanResultsRepository {
    ScanResults load(InputStream is) throws IOException;

    void save(ScanResults scanResults, OutputStream os) throws IOException;
}
