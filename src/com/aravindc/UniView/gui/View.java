/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package com.aravindc.UniView.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.aravindc.UniView.unipen.PenPoint;
import com.aravindc.UniView.unipen.PenUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

/**
 *
 * @author  __USER__
 */
public class View extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hashtable<String, PenPoint> boundingBox;
	/** Creates new form View */
	public View() {
		getContentPane().setBackground(Color.GRAY);
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		fileChooser = new javax.swing.JFileChooser();
		jPanel1 = new Canvas();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("UniView");
		setBackground(new java.awt.Color(102, 102, 102));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 645,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 485,
				Short.MAX_VALUE));

		fileMenu.setText("File");

		openMenuItem.setText("Open");
		openMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(openMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(30)
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(16)
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			CanvasDrawer worker = new CanvasDrawer();
			worker.execute();
			
		} else {
			System.out.println("File access cancelled by user.");
		}
	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new View().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JMenu fileMenu;
	private Canvas jPanel1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openMenuItem;
	// End of variables declaration//GEN-END:variables
	
	private class CanvasDrawer extends SwingWorker<Hashtable<String, PenPoint>, Object>{

		@Override
		protected Hashtable<String, PenPoint> doInBackground() throws Exception {
			System.out.println("doInBackground()");
			File unipenFile = fileChooser.getSelectedFile();
			ArrayList<PenPoint> points = PenUtils.readPointsFromFile(unipenFile);
			boundingBox = PenUtils.getBoundingBox(points);
			System.out.println(PenUtils.TOP_LEFT + boundingBox.get(PenUtils.TOP_LEFT));
			jPanel1.flag = true;
			jPanel1.repaint();
			return null;
		}
		
		@Override
		protected void done() {
			// TODO Auto-generated method stub
			super.done();
			
		}
	}
	private class Canvas extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public  boolean flag = false;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(flag){
				System.out.println("entered paint component");
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLUE);
			g2d.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setStroke(new BasicStroke(8,
			            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g.drawRoundRect(boundingBox.get(PenUtils.TOP_LEFT).getX(),boundingBox.get(PenUtils.TOP_LEFT).getY() , 
					(boundingBox.get(PenUtils.BOTTOM_RIGHT).getX())-(boundingBox.get(PenUtils.TOP_LEFT).getX()), 
					(boundingBox.get(PenUtils.BOTTOM_RIGHT).getY())-(boundingBox.get(PenUtils.TOP_LEFT).getY()), 0, 0);
			}
			
		}
	}
}