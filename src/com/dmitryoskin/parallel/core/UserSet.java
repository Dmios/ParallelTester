package com.dmitryoskin.parallel.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Dmitry Oskin
 */
public class UserSet implements Serializable {

	private static final long serialVersionUID = 7328957238957329857L;

	private int processCount = 1;

	private transient Map<String, Boolean> hosts = new LinkedHashMap<>();


	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();

		out.writeInt(hosts.size());
		for (Map.Entry<String, Boolean> entry : hosts.entrySet()) {
			out.writeUTF(entry.getKey());
			out.writeBoolean(entry.getValue());
		}
	}

	private void readObject(ObjectInputStream in) throws Exception {
		in.defaultReadObject();

		hosts = new LinkedHashMap<>();

		int hostsCount = in.readInt();
		for (int i = 0; i < hostsCount; i++) {
			String host = in.readUTF();
			boolean active = in.readBoolean();
			hosts.put(host, active);
		}
	}

	public Map<String, Boolean> getHosts() {
		return hosts;
	}

	public void setHosts(Map<String, Boolean> hosts) {
		this.hosts = hosts;
	}

	public int getProcessCount() {
		return processCount;
	}

	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}

}
