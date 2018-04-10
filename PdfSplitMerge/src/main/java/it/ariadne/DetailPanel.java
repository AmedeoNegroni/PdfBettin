package it.ariadne;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DetailPanel extends JPanel{

	public DetailPanel() {
		
		String[] months = new String[] {"Gen","Feb","Mar","Apr","Mag",
										"Giu","Lug","Ago","Set","Ott",
										"Nov","Dic"};
		
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height = 500;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Cedolini Ariadne"));

		//Seleziona mese
		JLabel monthLabel = new JLabel("Mese: ");
		JComboBox<String> monthBox = new JComboBox<>(months);

		//Seleziona anno
		JLabel yearLabel = new JLabel("Anno: ");
		JTextField yearField = new JTextField(20);

		//Seleziona il pdf da splittare
		JLabel pdfLabel = new JLabel("Seleziona il file pdf: ");
		JTextField pdfField = new JTextField(20);
		pdfField.setEditable(false);
		JScrollPane scrollPdf = new JScrollPane(pdfField);
		JButton pdfButton = new JButton("Sfoglia pdf");
		pdfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("File Browser");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            JFileChooser fileChooser = new JFileChooser(".");
	            fileChooser.setControlButtonsAreShown(true);
	            fileChooser.showOpenDialog(frame);

	            File selectedFile = fileChooser.getSelectedFile();
	            
	            if (selectedFile != null) {
	            	String path = fileChooser.getSelectedFile().getAbsolutePath();
	            	pdfField.setText(path);
	            }
	            
	            frame.pack();
	            frame.setVisible(true);
	        }
			
		});
		
		//Seleziona il file excel da cui leggere le password
		JLabel excelLabel = new JLabel("Seleziona il file Excel: ");
		JTextField excelField = new JTextField(20);
		excelField.setEditable(false);
		JScrollPane scrollExcel = new JScrollPane(excelField);
		JButton excelButton = new JButton("Sfoglia Excel");
		excelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("File Browser");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            JFileChooser fileChooser = new JFileChooser(".");
	            fileChooser.setControlButtonsAreShown(true);
	            fileChooser.showOpenDialog(frame);

	            File selectedFile = fileChooser.getSelectedFile();
	            
	            if (selectedFile != null) {
	            	String path = fileChooser.getSelectedFile().getAbsolutePath();
	            	excelField.setText(path);
	            }
	            
	            frame.pack();
	            frame.setVisible(true);
	        }
		});
		
		//Seleziona la cartella di destinazione
		JLabel folderLabel = new JLabel("Seleziona la cartella di destinazione: ");
		JTextField folderField = new JTextField(20);
		folderField.setEditable(false);
		JScrollPane scrollFolder = new JScrollPane(folderField);
		JButton folderButton = new JButton("Sfoglia cartella");
		folderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("File Browser");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            JFileChooser fileChooser = new JFileChooser(".");
	            fileChooser.setControlButtonsAreShown(true);
	            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            fileChooser.setAcceptAllFileFilterUsed(false);
	           
	            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            		String path = fileChooser.getSelectedFile().getAbsolutePath();
	            		folderField.setText(path);
	           	} 
	            
	            frame.pack();
	            frame.setVisible(true);
	            
	        }
		});
		
		//Invia i dati alle classi java
		JButton sendData = new JButton("Splitta!");
		sendData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean done = false;
				String month = monthBox.getSelectedItem().toString();
				String year = yearField.getText();
				String pdfFile = pdfField.getText();
				String excelFile = excelField.getText();
				String folderPath = folderField.getText();
				
				if (year.equals("") || pdfFile.equals("") || excelFile.equals("") || folderPath.equals("")) {
					JFrame messageFrame = new JFrame();
					JOptionPane.showMessageDialog(messageFrame, "Inserire tutti i campi");
				} else {
					try {
					PdfSplit2.split(pdfFile, excelFile, month, year, folderPath);
					done = true;
					} catch(Exception ex) {
						JFrame messageFrame = new JFrame();
						JOptionPane.showMessageDialog(messageFrame, "Errore nell'inserire i file");
					}
					if (done) {
						JFrame messageFrame = new JFrame();
						JOptionPane.showMessageDialog(messageFrame, "Finito");
						System.exit(0);
					}
				}
			}
		});
		
		//Chiudi la finestra
		JButton close = new JButton("Annulla e esci");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//Crea il layout, la griglia e posiziona gli elementi
		setLayout(new GridBagLayout());
		
		GridBagConstraints gr = new GridBagConstraints();
		
		gr.gridx = 0;
		gr.gridy = 0;
		add(monthLabel,gr);
		
		gr.gridx = 2;
		gr.gridy = 0;
		add(monthBox,gr);
		
		gr.gridx = 0;
		gr.gridy = 1;
		add(yearLabel,gr);
		
		gr.gridx = 2;
		gr.gridy = 1;
		add(yearField,gr);
		
		gr.gridx = 0;
		gr.gridy = 2;
		add(pdfLabel,gr);
		
		gr.gridx = 1;
		gr.gridy = 2;
		add(pdfButton,gr);
		
		gr.gridx = 2;
		gr.gridy = 2;
		add(scrollPdf,gr);
		
		gr.gridx = 0;
		gr.gridy = 3;
		add(excelLabel,gr);
		
		gr.gridx = 1;
		gr.gridy = 3;
		add(excelButton,gr);
		
		gr.gridx = 2;
		gr.gridy = 3;
		add(scrollExcel,gr);
		
		gr.gridx = 0;
		gr.gridy = 4;
		add(folderLabel,gr);
		
		gr.gridx = 1;
		gr.gridy = 4;
		add(folderButton,gr);
		
		gr.gridx = 2;
		gr.gridy = 4;
		add(scrollFolder,gr);
		
		gr.gridx = 1;
		gr.gridy = 5;
		add(sendData,gr);
		
		gr.gridx = 2;
		gr.gridy = 5;
		add(close,gr);
		
	}
	
}
