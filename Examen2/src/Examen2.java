import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Examen2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtTfno;
	private JList<String> lstTelefonos;
	private DefaultListModel<String> telefonosTemp;
	private JTextArea txtMensaje;
	private JButton btnAdd;
	private JButton btnEliminar;
	private JButton btnPasarAMayus;
	private JButton btnPasarAMinus;
	private JButton btnCargarFichero;
	private JRadioButton rdbtnFich1;
	private JRadioButton rdbtnFich2;
	private JRadioButton rdbtnFich3;
	private JRadioButton rdbtnFich4;
	private ButtonGroup grpFicheros;
	private JButton btnEnviar;
	private JTextField txtNumCar;
	private JTextField txtFaltan;
	private String telefono;
	private BBDD baseDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Examen2 frame = new Examen2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Examen2() {
		setTitle("SMS Almi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEnviar = new JLabel("Enviar (1 \u00F3 m\u00E1s)");
		lblEnviar.setBounds(55, 25, 120, 16);
		contentPane.add(lblEnviar);

		JLabel lblTextoDelMensaje = new JLabel("Texto del mensaje (m\u00E1x. 120 caracteres)");
		lblTextoDelMensaje.setBounds(223, 25, 257, 16);
		contentPane.add(lblTextoDelMensaje);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 55, 138, 178);
		contentPane.add(scrollPane);

		lstTelefonos = new JList();
		scrollPane.setViewportView(lstTelefonos);
		
		telefonosTemp = new DefaultListModel<String>();
		lstTelefonos.setModel(telefonosTemp);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(223, 53, 334, 184);
		contentPane.add(scrollPane_1);

		txtMensaje = new JTextArea();
		scrollPane_1.setViewportView(txtMensaje);

		JLabel lblNumCaracteres = new JLabel("Num Car:");
		lblNumCaracteres.setBounds(569, 57, 67, 16);
		contentPane.add(lblNumCaracteres);

		JLabel lblFaltan = new JLabel("Faltan:");
		lblFaltan.setBounds(569, 86, 56, 16);
		contentPane.add(lblFaltan);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(44, 310, 56, 25);
		contentPane.add(btnAdd);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(101, 310, 79, 25);
		contentPane.add(btnEliminar);

		txtTfno = new JTextField();
		txtTfno.setBounds(42, 275, 133, 22);
		contentPane.add(txtTfno);
		txtTfno.setColumns(10);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(42, 246, 56, 16);
		contentPane.add(lblTelfono);

		btnPasarAMayus = new JButton("Pasar a MAYUS.");
		btnPasarAMayus.setBounds(234, 274, 133, 25);
		contentPane.add(btnPasarAMayus);

		btnPasarAMinus = new JButton("Pasar a minus.");
		btnPasarAMinus.setBounds(395, 274, 133, 25);
		contentPane.add(btnPasarAMinus);

		rdbtnFich1 = new JRadioButton("Fichero1.txt");
		rdbtnFich1.setSelected(true);
		rdbtnFich1.setBounds(619, 200, 109, 25);
		contentPane.add(rdbtnFich1);

		rdbtnFich2 = new JRadioButton("Fichero2.txt");
		rdbtnFich2.setBounds(619, 230, 109, 25);
		contentPane.add(rdbtnFich2);

		rdbtnFich3 = new JRadioButton("Fichero3.txt");
		rdbtnFich3.setBounds(619, 260, 109, 25);
		contentPane.add(rdbtnFich3);

		rdbtnFich4 = new JRadioButton("Fichero4.txt");
		rdbtnFich4.setBounds(619, 289, 109, 25);
		contentPane.add(rdbtnFich4);

		grpFicheros=new ButtonGroup();
		grpFicheros.add(rdbtnFich1);
		grpFicheros.add(rdbtnFich2);
		grpFicheros.add(rdbtnFich3);
		grpFicheros.add(rdbtnFich4);

		btnCargarFichero = new JButton("Cargar Fichero");
		btnCargarFichero.setBounds(595, 320, 133, 25);
		contentPane.add(btnCargarFichero);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(334, 341, 97, 25);
		contentPane.add(btnEnviar);

		txtNumCar = new JTextField();
		txtNumCar.setBackground(Color.LIGHT_GRAY);
		txtNumCar.setEditable(false);
		txtNumCar.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumCar.setText("0");
		txtNumCar.setBounds(644, 54, 67, 22);
		contentPane.add(txtNumCar);
		txtNumCar.setColumns(10);

		txtFaltan = new JTextField();
		txtFaltan.setBackground(Color.LIGHT_GRAY);
		txtFaltan.setEditable(false);
		txtFaltan.setHorizontalAlignment(SwingConstants.CENTER);
		txtFaltan.setText("120");
		txtFaltan.setBounds(644, 86, 67, 22);
		contentPane.add(txtFaltan);
		txtFaltan.setColumns(10);
		
		registrarEventos();
	}

	private void registrarEventos() {
		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enviarABase();
			}
		});
		
		lstTelefonos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnEliminar.setEnabled(true);
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarTelefonos();
				btnEliminar.setEnabled(false);
			}
		});
		
		btnCargarFichero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarFicheros();
				actualizarCar();
			}
		});

		btnPasarAMayus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String textoAlt;
				textoAlt=txtMensaje.getText().toUpperCase();
				txtMensaje.setText(textoAlt);
			}
		});

		btnPasarAMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String textoAlt;
				textoAlt=txtMensaje.getText().toLowerCase();
				txtMensaje.setText(textoAlt);
			}
		});

		txtMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				actualizarCar();
			}
		});

		txtTfno.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()<KeyEvent.VK_0 || arg0.getKeyChar()>KeyEvent.VK_9){
					arg0.consume();
				}				
			}
		});

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtTfno.getText().equals("")){
					agregarTelefonos();

				} else {
					JOptionPane.showMessageDialog(null, this, "No puedes dejar el campo vacio", 0);

				}

			}
		});		
	}

	protected void enviarABase() {
		baseDatos= new BBDD();
		baseDatos.conectar();
		for (int i = 0; i < telefonosTemp.size(); i++) {
			baseDatos.insertarRegistros(telefonosTemp.indexOf(i), txtMensaje+"");
		}
		
	}

	protected void eliminarTelefonos() {
		telefonosTemp.removeElementAt(lstTelefonos.getSelectedIndex());
	}

	protected void cargarFicheros() {
		FileDialog dlgCargar;
		BufferedReader br;
		String fich="Fichero1.txt", ruta, linea;
		if (rdbtnFich1.isSelected()) {
			fich="Fichero1.txt";
		} else if (rdbtnFich2.isSelected()) {
			fich="Fichero2.txt";
		} else if (rdbtnFich3.isSelected()) {
			fich="Fichero3.txt";
		} else if (rdbtnFich4.isSelected()) {
			fich="Fichero4.txt";
		}

		//dlgCargar = new FileDialog(this, "Cargar archivo", FileDialog.LOAD);
		//dlgCargar.setVisible(true);
		//if((fich=dlgCargar.getFile())!=null){//NO PULSA CANCELAR
			//ruta=dlgCargar.getDirectory();
			try {
				br = new BufferedReader(new FileReader(fich));
				txtMensaje.setText(br.readLine());
				while((linea=br.readLine())!=null){
					txtMensaje.append(linea+"\n");
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		
	}

	protected void actualizarCar() {
		int maximo = 120;
		txtNumCar.setText(txtMensaje.getText().length()+"");
		txtFaltan.setText(maximo-txtMensaje.getText().length()+"");
		if (txtMensaje.getText().length()>=100 && txtMensaje.getText().length()<=120) {
			txtNumCar.setBackground(Color.YELLOW);
		} else if (txtMensaje.getText().length()>120){
			txtNumCar.setBackground(Color.RED);
		} else {
			txtNumCar.setBackground(Color.LIGHT_GRAY);
		}
	}

	protected void agregarTelefonos() {
		telefono=txtTfno.getText();
		telefonosTemp.addElement(telefono);
		txtTfno.setText("");
	}
}
