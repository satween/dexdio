package com.giyeok.dexdio.dexreader.structs;

import java.io.IOException;

import com.giyeok.dexdio.dexreader.EndianRandomAccessFile;
import com.giyeok.dexdio.dexreader.value.Array;
import com.giyeok.dexdio.dexreader.value.ULeb128;
import com.giyeok.dexdio.dexreader.value.Value;

public class encoded_catch_handler_list extends Value {
	private ULeb128 size;
	private Array list;

	@Override
	public void read(EndianRandomAccessFile stream) throws IOException {
		size = new ULeb128();
		size.read(stream);
		
		list = new Array(encoded_catch_handler.class, (int) size.getValue());
		list.read(stream);
	}
	
	public int size() {
		return (int) size.getValue();
	}
	
	public encoded_catch_handler[] list() {
		return list.asArray(new encoded_catch_handler[0]);
	}

	@Override
	public int getByteLength() {
		return size.getByteLength() + list.getByteLength();
	}
	
	public int getStartingOffset() {
		return size.getByteLength();
	}
}
