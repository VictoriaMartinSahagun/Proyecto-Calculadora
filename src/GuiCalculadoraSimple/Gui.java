package GuiCalculadoraSimple;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import LogicaCalculadoraSimple.CalculadoraSimple;

import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Label;

public class Gui extends JFrame{
	private ArrayList<String> nombrePlugs;
	private CalculadoraSimple calculadora;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Gui() {
		
		//Frame
		setTitle("Calculadora simple");
		setIconImage(new ImageIcon(this.getClass().getResource("/img/iconoCalculadora.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Paneles
		JPanel panelCarga = new JPanel();
		getContentPane().add(panelCarga, BorderLayout.SOUTH);
		panelCarga.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelCalculadora = new JPanel();
		getContentPane().add(panelCalculadora, BorderLayout.CENTER);
		panelCalculadora.setLayout(null);
		
		//Labels
		JLabel Nombre = new JLabel("Calculadora Simple");
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(Nombre, BorderLayout.NORTH);
		
		JLabel primerParametro = new JLabel("Parametro 1");
		primerParametro.setBounds(33, 45, 94, 14);
		panelCalculadora.add(primerParametro);
		
		JLabel SegundoParametro = new JLabel("Parametro 2");
		SegundoParametro.setBounds(137, 45, 94, 14);
		panelCalculadora.add(SegundoParametro);
		

		JLabel resultado = new JLabel();
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		resultado.setBounds(33, 143, 191, 22);
		panelCalculadora.add(resultado);
		
		Label textoOperacion = new Label("Seleccione la operacion a realizar");
		textoOperacion.setBounds(261, 37, 191, 22);
		panelCalculadora.add(textoOperacion);
		
		//Calculadora y cargas
		calculadora = new CalculadoraSimple();
		calculadora.getPlugins();
		nombrePlugs=calculadora.getPluginsName();
		
		//ComboBoxs
		JComboBox<Integer> opcionesParam1 = new JComboBox<Integer>();
		opcionesParam1.setBounds(33, 70, 60, 22);
		panelCalculadora.add(opcionesParam1);
		
		JComboBox<Integer> opcionesParam2 = new JComboBox<Integer>();
		opcionesParam2.setBounds(137, 70, 60, 22);
		panelCalculadora.add(opcionesParam2);
		
		for(int i=0;i<10;i++) {
			opcionesParam1.addItem(i);
			opcionesParam2.addItem(i);
		}
		
		//Choice
		Choice opcionesPlugins = new Choice();
		opcionesPlugins.setBounds(261, 70, 191, 20);
		cargarOpcionesPlugins(opcionesPlugins);
		panelCalculadora.add(opcionesPlugins);
		
		//Botones
		JButton accionarOperaciones = new JButton("Realizar Operacion");
		accionarOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Realizar operacion con Plugin seleccionado
				int resObt=0;
				String pluginSeleccionado = opcionesPlugins.getItem(opcionesPlugins.getSelectedIndex());
				int param1 = opcionesParam1.getItemAt(opcionesParam1.getSelectedIndex());
				int param2 = opcionesParam2.getItemAt(opcionesParam2.getSelectedIndex());
				try {
					resObt = calculadora.runPlugin(param1, param2, pluginSeleccionado);
					resultado.setText("Resultado obtenido: "+resObt);
				}catch(ArithmeticException ex) {
					resultado.setText("Parametros invalidos");
				}
			}
		});
		accionarOperaciones.setBounds(261, 143, 191, 22);
		panelCalculadora.add(accionarOperaciones);
		
		JButton botonAct = new JButton("Actualizar");
		botonAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cargo nuevo Plugins
				calculadora.getPlugins();
				nombrePlugs=calculadora.getPluginsName();
				cargarOpcionesPlugins(opcionesPlugins);
			}
		});
		panelCarga.add(botonAct);
	}
	
	private void cargarOpcionesPlugins(Choice opciones) {
		int fin= nombrePlugs.size();
		for(int i=0;i<fin;i++) {
			opciones.add(nombrePlugs.get(i));
		}
	}
}
