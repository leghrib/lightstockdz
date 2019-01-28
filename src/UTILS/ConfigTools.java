package UTILS;


import java.awt.Color;
import java.awt.Point;

public class ConfigTools {
	private final String SPLITSEQUENCE = "\t%%\t";
	private final String SPLITOFDATA = "%";
	FileTools ftools;


	public ConfigTools(String fileURL) {
		ftools = new FileTools(fileURL);

	}

	public void removeAttribute(String name) {
		ftools.deleteFirstLineStart(name + SPLITSEQUENCE);
	}

	// Integer=============================================================
	public void addIntAttribute(String name, int value) {
		if (!isExistAttribute(name))
			ftools.addLineAtFirst(name + SPLITSEQUENCE + "String"
					+ SPLITSEQUENCE + value);
	}

	public int getIntValue(String name)  {
		try{
		return Integer.valueOf(getStringValue(name)).intValue();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public void setIntAttribute(String name, int value) {
		removeAttribute(name);
		addIntAttribute(name, value);
	}

	// String==================================================================
	public void setStringAttribute(String name, String value) {
		removeAttribute(name);
		addStringAttribute(name, value);
	}

	public String getStringValue(String name) {
		String line = ftools.getLineStart(name + SPLITSEQUENCE);
		try{
		return line.split(SPLITSEQUENCE)[2];
		}catch (Exception e) {
			return "";
		}
	}

	public void addStringAttribute(String name, String value) {
		if (!isExistAttribute(name))
			ftools.addLineAtFirst(name + SPLITSEQUENCE + "String"
					+ SPLITSEQUENCE + value);
	}

	public void addPointAttribute(String name, Point p) {
		if (!isExistAttribute(name))
			addPointAttribute(name, p.x, p.y);
	}

	// Point=====================================================================
	public void addPointAttribute(String name, int x, int y) {
		if (!isExistAttribute(name))
			ftools.addLineAtFirst(name + SPLITSEQUENCE + "Point"
					+ SPLITSEQUENCE + x + SPLITOFDATA + y);
	}

	public Point getPointValue(String name) throws Exception {
		
		String data = getStringValue(name);
		int x = Integer.valueOf(data.split(SPLITOFDATA)[0]).intValue();
		int y = Integer.valueOf(data.split(SPLITOFDATA)[1]).intValue();
		return new Point(x, y);

	}

	public void setPointAttribute(String name, Point p) {
		removeAttribute(name);
		addPointAttribute(name, p);
	}

	// boolean===================================================================
	public void addBooleanAttribute(String name, boolean b) {
		if (!isExistAttribute(name))
			ftools.addLineAtFirst(name + SPLITSEQUENCE + "boolean"
					+ SPLITSEQUENCE + b);
	}

	public void setBooleanAttribute(String name, boolean value) {
		removeAttribute(name);
		addBooleanAttribute(name, value);
	}

	public boolean isExistAttribute(String name) {
		return ftools.IsExistLineStart(name + SPLITSEQUENCE);
	}

	public boolean getBooleanValue(String name) throws Exception {
		String value = getStringValue(name);
		if (value.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	// Color===========================================================================

	public void addColorAttribute(String name, Color c) {
		if (!isExistAttribute(name))
			ftools.addLineAtFirst(name + SPLITSEQUENCE + "Color"
					+ SPLITSEQUENCE + c.getRed() + SPLITOFDATA + c.getGreen()
					+ SPLITOFDATA + c.getBlue() + SPLITOFDATA + c.getAlpha());

	}

	public void setColorAttribute(String name, Color c) {
		removeAttribute(name);
		addColorAttribute(name, c);
	}

	public Color getColorValue(String name) {
		try {
			String data = getStringValue(name);
			int x = Integer.valueOf(data.split(SPLITOFDATA)[0]).intValue();
			int y = Integer.valueOf(data.split(SPLITOFDATA)[1]).intValue();
			int z = Integer.valueOf(data.split(SPLITOFDATA)[2]).intValue();
			int a = Integer.valueOf(data.split(SPLITOFDATA)[3]).intValue();
			return new Color(x, y, z, a);
		} catch (Exception e) {
			return new Color(0, 0, 0);
		}

	}

}
/*----------------------------------------------------------------
 *  Author:        Leghrib Badreddine
 *  Written:       2014
 *  Last updated:  20/02/2015
 *///-------------------------------------------------------------