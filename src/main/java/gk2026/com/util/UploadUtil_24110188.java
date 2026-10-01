package gk2026.com.util;

import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class UploadUtil_24110188 {
	private UploadUtil_24110188() {
	}

	public static String save(Part p, String root) throws IOException {
		if (p == null || p.getSize() == 0)
			return null;
		String n = Paths.get(p.getSubmittedFileName()).getFileName().toString();
		String file = UUID.randomUUID() + "_" + n;
		Path dir = Paths.get(root, "uploads");
		Files.createDirectories(dir);
		p.write(dir.resolve(file).toString());
		return "uploads/" + file;
	}
}
