package com.epam.factory;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import com.epam.model.DirectoryList;
import com.epam.model.List;

public class DirectoryListFactory implements ListFactory {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getGui(String directory) {
		DirectoryList list = new DirectoryList();
		File f = new File(System.getProperty(directory));
		JList fileList = new JList(f.listFiles());
		fileList.setCellRenderer(new DirectoryListFactory.FileRenderer(false));
		fileList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
		fileList.setVisibleRowCount(-1);
		list.setDirectoryFiles(fileList);
		return list;
	}
	
	@SuppressWarnings("serial")
	class FileRenderer extends DefaultListCellRenderer {

		private boolean pad;
		private Border padBorder = new EmptyBorder(3, 3, 3, 3);

		FileRenderer(boolean pad) {
			this.pad = pad;
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			Component c = super.getListCellRendererComponent(list, value, index,
					isSelected, cellHasFocus);
			JLabel l = (JLabel) c;
			File f = (File) value;
			l.setText(f.getName());
			l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
			if (pad) {
				l.setBorder(padBorder);
			}

			return l;
		}
	}

}
