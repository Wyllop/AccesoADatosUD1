package tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
	private JFrame frame;
	private JTextArea outputTextArea;
	private JTextField searchField;
	private JTextField autorField;
	private JTextField isbnField;
	private JTextField usuarioField;
	private JList<String> libroLista;
	private JList<String> registroLista;
	private List<Libro> libros = new ArrayList<>();
	private List<RegistroPrestamo> registrosPrestamo = new ArrayList<>();

	private final String LIBROS_FILE = "C:\\Users\\guill\\eclipse-workspace\\ProyectoUnidad1\\libros.txt" ;
	private final String PRESTAMOS_FILE = "C:\\Users\\guill\\eclipse-workspace\\ProyectoUnidad1\\prestamos.txt" ;

	public BibliotecaApp() {
		cargarLibros();
		cargarRegistros();
		initializeUI();
	}

	private void initializeUI() {
		frame = new JFrame("Gestión de Biblioteca");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		JPanel mainPanel = new JPanel(new BorderLayout());
		outputTextArea = new JTextArea();
		mainPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

		JPanel leftPanel = createLeftPanel();
		mainPanel.add(leftPanel, BorderLayout.WEST);

		JPanel rightPanel = createRightPanel();
		mainPanel.add(rightPanel, BorderLayout.EAST);

		frame.getContentPane().add(mainPanel);

		frame.setVisible(true);
	}

	private JPanel createLeftPanel() {
		JPanel leftPanel = new JPanel(new BorderLayout());

		DefaultListModel<String> bookListModel = new DefaultListModel<>();
		libroLista = new JList<>(bookListModel);
		JScrollPane bookScrollPane = new JScrollPane(libroLista);
		bookScrollPane.setPreferredSize(new Dimension(200, 200));
		leftPanel.add(bookScrollPane, BorderLayout.CENTER);

		JPanel searchPanel = new JPanel();
		searchField = new JTextField(20);
		autorField = new JTextField(20);
		isbnField = new JTextField(20);
		JButton buscarButton = new JButton("Buscar");
		JButton agregarLibroButton = new JButton("Agregar Libro");
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		searchPanel.add(new JLabel("Título:"), gbc);
		gbc.gridy++;
		searchPanel.add(searchField, gbc);
		gbc.gridy++;
		searchPanel.add(new JLabel("Autor:"), gbc);
		gbc.gridy++;
		searchPanel.add(autorField, gbc);
		gbc.gridy++;
		searchPanel.add(new JLabel("ISBN:"), gbc);
		gbc.gridy++;
		searchPanel.add(isbnField, gbc);
		gbc.gridy++;
		searchPanel.add(buscarButton, gbc);
		gbc.gridy++;
		searchPanel.add(agregarLibroButton, gbc);

		leftPanel.add(searchPanel, BorderLayout.NORTH);

		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titulo = searchField.getText();
				String autor = autorField.getText();
				String isbn = isbnField.getText();

				// Actualiza el área de texto de salida con los resultados
				outputTextArea
						.setText("¿Que libro quieres buscar? \nTítulo: " + titulo + "\nAutor: " + autor + "\nISBN: " + isbn);
			}
		});

		agregarLibroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titulo = searchField.getText();
				String autor = autorField.getText();
				String isbn = isbnField.getText();
				Libro nuevoLibro = new Libro(titulo, autor, isbn);
				libros.add(nuevoLibro);
				guardarLibros(nuevoLibro);
				DefaultListModel<String> model = (DefaultListModel<String>) libroLista.getModel();
				model.addElement(nuevoLibro.getTitulo());
				outputTextArea.setText("El Libro: " + nuevoLibro.getTitulo()+ "ha sido registrado");
				searchField.setText("");
				autorField.setText("");
				isbnField.setText("");
			}
		});

		return leftPanel;
	}

	private JPanel createRightPanel() {
		JPanel rightPanel = new JPanel(new BorderLayout());

		DefaultListModel<String> registroListModel = new DefaultListModel<>();
		registroLista = new JList<>(registroListModel);
		JScrollPane registroScrollPane = new JScrollPane(registroLista);
		registroScrollPane.setPreferredSize(new Dimension(200, 200));
		rightPanel.add(registroScrollPane, BorderLayout.CENTER);

		JPanel registroPanel = new JPanel();
		usuarioField = new JTextField(20);
		JButton prestamoButton = new JButton("Registrar Préstamo");
		JButton devolucionButton = new JButton("Registrar Devolución");
		registroPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		registroPanel.add(new JLabel("Usuario:"), gbc);
		gbc.gridy++;
		registroPanel.add(usuarioField, gbc);
		gbc.gridy++;
		registroPanel.add(prestamoButton, gbc);
		gbc.gridy++;
		registroPanel.add(devolucionButton, gbc);

		rightPanel.add(registroPanel, BorderLayout.NORTH);

		prestamoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = usuarioField.getText();
				int selectedBookIndex = libroLista.getSelectedIndex();
				if (selectedBookIndex >= 0) {
					Libro libroPrestado = libros.get(selectedBookIndex);
					RegistroPrestamo registro = new RegistroPrestamo(libroPrestado, usuario);
					registrosPrestamo.add(registro);
					guardarRegistros();
					DefaultListModel<String> model = (DefaultListModel<String>) registroLista.getModel();
					model.addElement(libroPrestado.getTitulo() + " a " + usuario);
					outputTextArea.setText("Préstamo registrado: " + libroPrestado.getTitulo() + " a " + usuario);
					usuarioField.setText("");
				}
			}
		});

		devolucionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRegistroIndex = registroLista.getSelectedIndex();
				if (selectedRegistroIndex >= 0) {
					RegistroPrestamo registro = registrosPrestamo.get(selectedRegistroIndex);
					registro.registrarDevolucion();
					// Actualiza el archivo de registros de préstamo con la devolución
					outputTextArea.setText("Devolución registrada para: " + registro.getLibro().getTitulo());
				}
			}
		});

		return rightPanel;
	}

	private void guardarLibros(Libro nuevoLibro) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(LIBROS_FILE, true));
			String libroData = nuevoLibro.getTitulo() + ";" + nuevoLibro.getAutor() + ";" + nuevoLibro.getIsbn();
			writer.println(libroData);
			writer.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void cargarLibros() {
		try (BufferedReader br = new BufferedReader(new FileReader(LIBROS_FILE))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				if (data.length >= 3) {
					Libro libro = new Libro(data[0], data[1], data[2]);
					libros.add(libro);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void guardarRegistros() {
		try (FileWriter writer = new FileWriter(PRESTAMOS_FILE)) {
			for (RegistroPrestamo registro : registrosPrestamo) {
				writer.write(registro.getLibro().getIsbn() + ";" + registro.getUsuario() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cargarRegistros() {
	    try (BufferedReader br = new BufferedReader(new FileReader(PRESTAMOS_FILE))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(";");
	            if (data.length >= 2) { // Asegurarse de que haya al menos 2 elementos en la línea
	                Libro libro = buscarLibroPorISBN(data[0]);
	                if (libro != null) { // Verificar si se encontró un libro
	                    RegistroPrestamo registro = new RegistroPrestamo(libro, data[1]);
	                    registrosPrestamo.add(registro);
	                } else {
	                    System.out.println("Advertencia: ISBN no encontrado para registro de préstamo.");
	                }
	            } else {
	                System.out.println("Advertencia: Formato de línea incorrecto en el archivo 'prestamos.txt'.");
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private Libro buscarLibroPorISBN(String isbn) {
		for (Libro libro : libros) {
			if (libro.getIsbn().equals(isbn)) {
				return libro;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BibliotecaApp app = new BibliotecaApp();
			}
		});
	}
}
