package com.zorz.mario.io.stream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A wrapper of an InputStream that provides some extra functionality.
 * Fix the problem in method {@link FlushedInputStream#skip(long)} for fails on slow connections
 * @see FilterInputStream
 * @author mzorz
 *
 */
public class FlushedInputStream extends FilterInputStream {

	/**
	 * Constructor
	 * @param in InputStream
	 */
	public FlushedInputStream(InputStream in) {
		super(in);
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.FilterInputStream#skip(long)
	 */
	@Override
	public long skip(long byteCount) throws IOException {
		long totalBytesSkipped = 0L;
		while (totalBytesSkipped < byteCount) {
			long bytesSkipped = in.skip(byteCount - totalBytesSkipped);
			if (bytesSkipped == 0L) {
				int b = read();
				if (b < 0) {
					break;
				} else {
					bytesSkipped = 1;
				}
			}
			totalBytesSkipped += bytesSkipped;
		}
		return totalBytesSkipped;
	}
}