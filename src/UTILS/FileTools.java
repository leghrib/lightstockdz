package UTILS;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;

/*
 * revised and optimised by Leghrib Badreddine
 * on 19 February 2015
 */
public class FileTools {
	File f;
	File tempFile = createTempFile();
	private static final int BUFFER_SIZE = 1024 * 50;

	// testing in main
	public static void main(String[] args) {
		long depart = Calendar.getInstance().getTimeInMillis();
		System.out.println(new FileTools("f.txt").IsExistLineStart("s"));
		System.out.println(Calendar.getInstance().getTimeInMillis() - depart);

	}

	public FileTools(String fileURL) {
		f = new File(fileURL);

	}

	/*
	 * return temp file for object filtools
	 */
	private File createTempFile() {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("FileToolsInstance", "tmp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;

	}

	/*
	 * 0 is the first line
	 */
	public boolean addLineAt(String line, int n) {
		try {
			copyFile(f.getPath(), tempFile.getPath());
			BufferedReader br = new BufferedReader(new FileReader(tempFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			int i = 0;
			try {
				while (true) {
					if (i == n) {
						bw.write(line);
						bw.newLine();
						break;
					}
					bw.write(br.readLine());
					bw.newLine();
					i++;
				}
				while (true) {// we use two boucle to stop incrementing i
					bw.write(br.readLine());
					bw.newLine();
				}
			} catch (Exception e) {
				bw.close();
				br.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addLineAtFirst(String line) {
		return addLineAt(line, 0);
	}

	/*
	 * return true if line add at last
	 */

	public boolean addLineAtLast(String line) {

		try {
			copyFile(f.getPath(), tempFile.getPath());
			BufferedReader br = new BufferedReader(new FileReader(tempFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			try {
				while (true) {
					bw.write(br.readLine());
					bw.newLine();
				}
			} catch (Exception e) {
				bw.write(line);
				bw.close();
				br.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isExistLine(String line) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				while (true) {
					if (br.readLine().equals(line))
						;
					return true;
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}
		return false;
	}

	public boolean viderFile() {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.write("");
			bw.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean deleteFirstLineStart(String st) {

		copyFile(f.getPath(), tempFile.getPath());
		boolean b = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(tempFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String s = "";

			try {
				while (true) {
					s = br.readLine();
					
					if(s.startsWith(st)&& b==false){
						b=true;
						continue;
					}

						bw.write(s);
						bw.newLine();
					

				}
			} catch (Exception e) {
				br.close();
				bw.close();
				return b;

			}

		} catch (Exception e) {
		}
		return b;

	}

	public int deleteAllLineStart(String st) {
		copyFile(f.getPath(), tempFile.getPath());
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(tempFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String s = "";

			try {
				while (true) {
					s = br.readLine();
					if (!s.startsWith(st)) {
						bw.write(s);
						bw.newLine();
					} else {
						i++;
					}

				}
			} catch (Exception e) {
				br.close();
				bw.close();
				return i;

			}

		} catch (Exception e) {
		}
		return i;

	}

	/*
	 * copy from a file to another return true in success operation
	 */
	public static boolean copyFile(String from, String to) {
		try {
			FileInputStream in = new FileInputStream(from);
			FileOutputStream out = new FileOutputStream(to);
			byte[] buffer = new byte[BUFFER_SIZE];
			int read_bytes;
			while ((read_bytes = in.read(buffer)) != -1)
				out.write(buffer, 0, read_bytes);
			in.close();
			out.close();
			return true;
		} catch (IOException e) {
			return false;
		}

	}

	/*
	 * get line at the order
	 */
	public String getLineAt(int n) {
		if (n < 0)
			return null;
		String s = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			int i = 0;
			try {
				while (true) {
					s = br.readLine();
					if (i == n) {
						return s;
					}
					i++;
				}
			} catch (Exception e) {
				br.close();
			}

		} catch (Exception e) {
		}
		return s;

	}

	/*
	 * get first line start
	 */
	public String getLineStart(String st) {
		String s = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				while (true) {
					if ((s = br.readLine()).startsWith(st))
						return s;

				}
			} catch (Exception e) {
				br.close();
			}

		} catch (Exception e) {

		}
		return s;

	}

	/*
 * 
 */
	public int getNumberLine() {

		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				while (br.readLine() != null) {
					i++;
				}
			} catch (Exception e) {
				br.close();
			}
		} catch (Exception e) {
		}
		return i;

	}

	/*
 * 
 */
	public String[] getTableLine() {
		int i = 0;
		String[] t = new String[getNumberLine()];
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s;
			try {
				while (true) {
					s = br.readLine();
					t[i] = s;
					i++;
				}
			} catch (Exception e) {
				br.close();
			}

		} catch (Exception e) {
		}
		return t;

	}

	/*
 * 
 */
	public boolean IsExistLineStart(String st) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			try {
				String s;
				while (true) {
					s = br.readLine();
					if (s.startsWith(st))
						return true;
				}
			} catch (Exception e) {
				br.close();
			}
		} catch (Exception e) {
		}
		return false;

	}

	/*
 * 
 */
	public static String getJarPath() {
		URL url = FileTools.class.getProtectionDomain().getCodeSource()
				.getLocation(); // Gets the path
		String jarPath = null;
		try {
			jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String parentPath = new File(jarPath).getParentFile().getPath();
		parentPath = parentPath + File.separator;

		return parentPath;
	}

}

/*----------------------------------------------------------------
 *  Author:        Leghrib Badreddine
 *  Written:       2014
 *  Last updated:  19/02/2015
 */// -------------------------------------------------------------