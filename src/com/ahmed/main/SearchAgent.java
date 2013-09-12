package com.ahmed.main;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.*;

//Search Agent
public class SearchAgent implements Runnable {
	String pattern;
	ConcurrentLinkedQueue<File> queue;
	ConcurrentLinkedQueue<String> searchResult;

	public boolean isRoot(File f) {
		File roots[] = File.listRoots();
		for (File i : roots) {
			if (f.getAbsolutePath().equals(i.getAbsolutePath()))
				return true;
		}
		return false;
	}

	public SearchAgent(ConcurrentLinkedQueue<File> queue,
			ConcurrentLinkedQueue<String> searchResult, String pattern) {

		this.queue = queue;
		this.searchResult = searchResult;
		this.pattern = pattern;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			while (queue.isEmpty() == false) {
				File current;
				synchronized (queue) {
					current = queue.remove();
				}
				if (current.getAbsolutePath().toLowerCase()
						.contains(pattern.toLowerCase()))
					synchronized (searchResult) {
						searchResult.add(current.getAbsolutePath());
					}
				if (current.isDirectory() || isRoot(current)) {
					File childern[] = current.listFiles();
					if (childern != null)
						for (File c : childern)
							synchronized (queue) {
								queue.add(c);
							}

				}
				//Thread.sleep(100);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
