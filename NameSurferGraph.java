
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	public ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();

	private NameSurferEntry nse;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */

	public NameSurferGraph() {
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		removeAll();
		drawBackGround();
		clearEntries();
		
	}
	
	private void clearEntries() { 
		int length = entries.size();
		for (int i = 0; i < length; i++) { 
			entries.remove(0);
	}
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawBackGround();
		drawGraph();
	}

	private void drawGraph() {
		for (int i = 0; i < entries.size(); i++) {
			nse = entries.get(i);
			for (int k = 0; k < NDECADES; k++) {
				redrawGraphs(k, i);
			}
		}
	}

	private void redrawGraphs(int k, int i) {
		// redraws the entire graph each time
		double y = 0;
		if (nse.getRank(k) == 0) {
			y = getHeight() - GRAPH_MARGIN_SIZE;
		} else {
			y = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * nse.getRank(k) / (double) MAX_RANK + GRAPH_MARGIN_SIZE;
		}
		redrawLines(k, y, i);
		redrawTitles(i, k, y);
	}

	private void redrawLines(int k, double y, int i) {
		// redraws the connecting lines
		double y2 = 0;
		double x = getWidth() / NDECADES * k;
		double x2 = getWidth() / NDECADES * (k + 1);
		if (k != NDECADES - 1) {
			if (nse.getRank(k + 1) == 0) {
				y2 = getHeight() - GRAPH_MARGIN_SIZE;
			} else {
				y2 = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * nse.getRank(k + 1) / (double) MAX_RANK + GRAPH_MARGIN_SIZE;
			}
		GLine line = new GLine(x, y, x2, y2);
		changeTheColor(line, i);
		add(line);
		}
	}

	private void redrawTitles(int i, int k, double y) {
		// puts names on the graph
		double x = getWidth() / NDECADES * k;
		GLabel titles;
		if (entries.get(i).getRank(k) > 0) {
			titles = new GLabel(entries.get(i).getName() + " " + entries.get(i).getRank(k), x, y);
		} else {
			titles = new GLabel(entries.get(i).getName() + " " + "*", x, y);
		}
		changeTheColor(titles, i);
		add(titles);
	}

	// changes the color of a GObject accordingly
	private void changeTheColor(GObject object, int i) {
		if (i % 4 == 0) {
			object.setColor(Color.BLACK);
		} else if (i % 4 == 1) {
			object.setColor(Color.RED);

		} else if (i % 4 == 2) {
			object.setColor(Color.BLUE);

		} else if (i % 4 == 3) {
			object.setColor(Color.YELLOW);
		}
	}
	
	// draws the vertical and horizontal lines, adds decade labels
	private void drawBackGround() {
		double diff = getWidth() / NDECADES;
		double xcrd = 0;
		int year = START_DECADE;
		for (int i = 0; i < NDECADES; i++) {
			String yearStr = year + "";
			add(new GLine(xcrd, 0, xcrd, getHeight()));
			add(new GLabel(yearStr, xcrd, getHeight()));
			xcrd = xcrd + diff;
			year = year + 10;
		}
		double ycrd = GRAPH_MARGIN_SIZE;
		add(new GLine(0, ycrd, getWidth(), ycrd));
		add(new GLine(0, getHeight() - ycrd, getWidth(), getHeight() - ycrd));

	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
