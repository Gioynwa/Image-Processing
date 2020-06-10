import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.*;
import java.io.*;


public class ImageProcessing extends JFrame {

	private PPMImage ppmImg;
	private YUVImage yuvImg;
	private BufferedImage bi;
	private JLabel label = new JLabel("", SwingConstants.CENTER);
	private ImageIcon icon;

	/*constructor*/

	public ImageProcessing() {

		initUI();
	}

    /*method to initialize JFrame*/

	private void initUI() {

		createMenuBar();

		setTitle("Image Processing");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(label);
	}

	/*Method to create MenuBar*/

	public void createMenuBar() {

		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("1. File");

		JMenu open = new JMenu("1.1 Open");
		JMenuItem openPPM = new JMenuItem("1.1.1 PPM File");
		openPPM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PPM Images","ppm"));
				int result = fileChooser.showOpenDialog(openPPM);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					try {

						ppmImg = new PPMImage(selectedFile);
						yuvImg = new YUVImage(ppmImg);

						showIcon();


					} catch(FileNotFoundException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					} catch(UnsupportedFileFormatException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					}
				}
			}
		});


		JMenuItem openYUV = new JMenuItem("1.1.2 YUV File");
		openYUV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("YUV Images","yuv"));
				int result = fileChooser.showOpenDialog(openYUV);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					try {

						yuvImg = new YUVImage(selectedFile);
						ppmImg = new PPMImage(yuvImg);

						showIcon();

					} catch(FileNotFoundException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					} catch(UnsupportedFileFormatException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					}
				}
			}
		});

		open.add(openPPM);
		open.add(openYUV);

		JMenu save = new JMenu("1.2 Save");
		JMenuItem savePPM = new JMenuItem("1.2.1 PPM File");
		savePPM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showSaveDialog(savePPM);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					ppmImg.toFile(selectedFile);
					JOptionPane.showMessageDialog(label, "Saved!");
				}
			}
		});

		JMenuItem saveYUV = new JMenuItem("1.2.2 YUV File");
		saveYUV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showSaveDialog(saveYUV);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					yuvImg.toFile(selectedFile);
					JOptionPane.showMessageDialog(label, "Saved!");
				}
			}
		});

		save.add(savePPM);
		save.add(saveYUV);

		file.add(open);
		file.add(save);

		JMenu actions = new JMenu("2. Actions");

		JMenuItem grayscaleMi = new JMenuItem("2.1 Grayscale");
		grayscaleMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				ppmImg.grayscale();
				yuvImg.grayscale();

				showIcon();
			}
		});

		JMenuItem doubleSizeMi = new JMenuItem("2.2 Increase Size");
		doubleSizeMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				ppmImg.doublesize();
				yuvImg.doublesize();

				showIcon();
			}
		});


		JMenuItem halfSizeMi = new JMenuItem("2.3 Decrease Size");
		halfSizeMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				ppmImg.halfsize();
				yuvImg.halfsize();

				showIcon();
			}
		});


		JMenuItem rotateClockwiseMi = new JMenuItem("2.4 Rotate Clockwise");
		rotateClockwiseMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				ppmImg.rotateClockwise();
				yuvImg.rotateClockwise();

				showIcon();
			}
		});


		JMenuItem histogramMi = new JMenuItem("2.5 Equalize Histogram");
		histogramMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(ppmImg == null) {
					JOptionPane.showMessageDialog(label, "Open a file first!");
					return;
				}

				Histogram h = new Histogram(yuvImg);
// 				h.toFile(new File("out1.txt"));
				h.equalize();
// 				h.toFile(new File("out2.txt"));
				yuvImg = new YUVImage(h.getEqualizedImage());
				ppmImg = new PPMImage(yuvImg);

				showIcon();

			}
		});


		actions.add(grayscaleMi);
		actions.add(doubleSizeMi);
		actions.add(halfSizeMi);
		actions.add(rotateClockwiseMi);
		actions.add(histogramMi);
		actions.addSeparator();

		JMenu sa = new JMenu("2.6 Stacking Algorithm");

		JMenuItem stackingMi = new JMenuItem("2.6.1 Select directory");
		stackingMi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(stackingMi);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					try {

						PPMImageStacker stackedImg = new PPMImageStacker(selectedFile);
						stackedImg.stack();
						ppmImg = new PPMImage(stackedImg.getStackedImage());
						yuvImg = new YUVImage(ppmImg);

						System.out.println("Stacking Done!");

						showIcon();
					} catch(FileNotFoundException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					} catch(UnsupportedFileFormatException ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(label, ex.getMessage());
					}
				}
			}
		});

		sa.add(stackingMi);

		actions.add(sa);
		menubar.add(file);
		menubar.add(actions);

		setJMenuBar(menubar);
	}

    /*method to convert ppm image to BufferedImage and then to ImageIcon to display it on the label*/

	public void showIcon() {

		bi = new BufferedImage(ppmImg.width, ppmImg.height, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < ppmImg.height; i++) {
			for(int j = 0; j < ppmImg.width; j++) {
				bi.setRGB(j, i, ppmImg.pixels[i][j].getPixel());
			}
		}

		icon = new ImageIcon(bi);
		label.setIcon(icon);

		pack();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				ImageProcessing ip = new ImageProcessing();
				ip.setVisible(true);
			}
		});
	}
}
