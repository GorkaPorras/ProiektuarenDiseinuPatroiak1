package gui;

import com.toedter.calendar.JCalendar;

import Iterator.ExtendedIterator;
import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Result;
import exceptions.EventFinished;
import exceptions.NotEnoughMoney;
import exceptions.SuperBetMinimumRes;
import exceptions.betMinimum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ApustuAnitzaGUI extends JFrame {

	private static BLFacade appFacadeInterface;
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	private static final long serialVersionUID = 1L;


	private Result re;
	private float inputprice;
	private Image img;
	private Image img1;
	private Image img2;

	private static Vector<Result> Results = new Vector<Result>();


	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel jLabelFees = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fees"));
	private final JLabel jLabelBasket = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fees"));
	private JLabel lblNewLabel_1;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel lblSeleccionaLaKuota;

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneFees = new JScrollPane();
	private JScrollPane scrollPaneBasket = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableFees = new JTable();
	private JTable tableBasket = new JTable();


	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelFees;
	private DefaultTableModel tableModelBasket;

	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesFees = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Result"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee"),

	};
	private String[] columnNamesBasket = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Event"),
			ResourceBundle.getBundle("Etiquetas").getString("Query"),
			ResourceBundle.getBundle("Etiquetas").getString("Result"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee"),

	};
	private final JLabel lblNewLabel = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel label_1 = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton papelera = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$


	public ApustuAnitzaGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		this.setResizable(false);

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(1300, 750));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MultipleResBet"));
		this.setLocationRelativeTo(null);
		jLabelEventDate.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelEventDate.setForeground(new Color(60, 179, 113));
		jLabelEventDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelEventDate.setBounds(new Rectangle(21, 34, 350, 20));

		jLabelQueries.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelQueries.setForeground(new Color(60, 179, 113));
		jLabelQueries.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelQueries.setBounds(857, 36, 425, 17);

		jLabelEvents.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelEvents.setForeground(new Color(60, 179, 113));
		jLabelEvents.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelEvents.setBounds(405, 35, 423, 18);

		jLabelFees.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelFees.setForeground(new Color(60, 179, 113));
		jLabelFees.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelFees.setBounds(21,395,425,20);

		jLabelBasket.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelBasket.setForeground(new Color(60, 179, 113));
		jLabelBasket.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelBasket.setBounds(478,395,804,20);


		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(jLabelFees);
		label_1.setBounds(519, 393, 34, 28);

		getContentPane().add(label_1);
		label_1.setVisible(false);
		papelera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Results.removeAllElements();
				tableModelBasket.setDataVector(null, columnNamesBasket);
				lblNewLabel_1.setText("");
				label_1.setVisible(false);
			}
		});
		papelera.setBounds(478, 398, 29, 23);

		getContentPane().add(papelera);
		img2 =  new ImageIcon(this.getClass().getResource("/Metal-Trash-256.png")).getImage().getScaledInstance(29, 23, Image.SCALE_SMOOTH);
		papelera.setIcon(new ImageIcon(img2));

		jButtonClose.setFont(new Font("Tahoma", Font.PLAIN, 18));

		jButtonClose.setBounds(new Rectangle(21, 665, 180, 40));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(21, 70, 350, 250));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{


			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						//	getEvents
						ExtendedIterator<Event> events = getBusinessLogic().getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						while (events.hasNext()) {
							Event ev = (Event) events.next();
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {
						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
				
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelFees.setDataVector(null, columnNamesFees);
			} 
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(403, 70, 425, 250));
		scrollPaneQueries.setBounds(new Rectangle(857, 70, 425, 250));
		scrollPaneFees.setBounds(new Rectangle(21, 428, 425, 224));
		scrollPaneBasket.setBounds(new Rectangle(478, 428, 804, 224));
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				Event ev = (Event) tableModelEvents.getValueAt(i,2); // obtain ev object

				//	domain
				Vector<Question> queries = getBusinessLogic().getQuestions(ev);

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3); // another column added to allocate qu objects

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+": "+ev.getDescription());

				for (Question qu:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(qu.getQuestionNumber());
					row.add(qu.getQuestion());
					row.add(qu); // qu object added in order to obtain it with tableModelQueries.getValueAt(i,2)
					tableModelQueries.addRow(row);
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
				
				tableModelFees.setDataVector(null, columnNamesFees);
			}
		});
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent q) {
				int i = tableQueries.getSelectedRow();
				Question qu = (Question) tableModelQueries.getValueAt(i,2); // obtain qu object

				//	domain
				Vector<Result> results = getBusinessLogic().getResults(qu);

				tableModelFees.setDataVector(null, columnNamesFees);
				tableModelFees.setColumnCount(3); // another column added to allocate re objects

				if (results.isEmpty())
					jLabelFees.setText(ResourceBundle.getBundle("Etiquetas").getString("NoResults")+": "+qu.getQuestion());
				else 
					jLabelFees.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedQuestion")+" "+qu.getQuestion());

				for (Result re:results){
					Vector<Object> row = new Vector<Object>();

					row.add(re.getResult());
					row.add(re.getFee());
					row.add(re); // re object added in order to obtain it with tableModelQueries.getValueAt(i,2)
					tableModelFees.addRow(row);
				}
				tableFees.getColumnModel().getColumn(0).setPreferredWidth(268);
				tableFees.getColumnModel().getColumn(1).setPreferredWidth(25);
				tableFees.getColumnModel().removeColumn(tableFees.getColumnModel().getColumn(2)); // not shown in JTable
			}
		});

		tableFees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent q) {
				//	erregistratuta dagoen konprobatzeko
				if(gui.UnRegisteredGUI.erab != null) {
					try {
						int i = tableFees.getSelectedRow();
						re = (Result) tableModelFees.getValueAt(i,2); // obtain re object

						Vector<Object> row = new Vector<Object>();

						Question q1 = getBusinessLogic().getResultContainer(re).getQuestion();
						Event ev1 = getBusinessLogic().getQuestionContainer(q1).getEvent();

						row.add(ev1.getDescription());
						row.add(q1.getQuestion());
						row.add(re.getResult());
						row.add(re.getFee());

						boolean aurkitua = false;
						for(Result r5: Results) {

							if(r5.getFeeNumber() == re.getFeeNumber()) {
								JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
								aurkitua = true;
								break;
							}

						}
						if(aurkitua == false) {
							Results.add(re);
							tableModelBasket.addRow(row);
						}
						if(isunlogicalbet()) {
							lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("zerochance"));
							label_1.setVisible(true);
						}
					}

					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		tableBasket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent q) {
				int i = tableBasket.getSelectedRow();

				Results.remove(i);
				if(!isunlogicalbet()) {
					lblNewLabel_1.setText("");
					label_1.setVisible(false);
				}

				tableModelBasket.removeRow(i);

			}
		});
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableEvents.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		tableEvents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableEvents.setRowHeight(25);

		DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer)tableEvents.getDefaultRenderer(Object.class);
		renderer1.setHorizontalAlignment( JLabel.CENTER );

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableQueries.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		tableQueries.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableQueries.setRowHeight(25);

		DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer)tableQueries.getDefaultRenderer(Object.class);
		renderer2.setHorizontalAlignment( JLabel.CENTER );

		scrollPaneFees.setViewportView(tableFees);
		tableModelFees = new DefaultTableModel(null,columnNamesFees) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		tableFees.setModel(tableModelFees);
		tableFees.getColumnModel().getColumn(0).setPreferredWidth(268);
		tableFees.getColumnModel().getColumn(1).setPreferredWidth(25);

		tableFees.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		tableFees.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableFees.setRowHeight(25);

		DefaultTableCellRenderer renderer3 = (DefaultTableCellRenderer)tableFees.getDefaultRenderer(Object.class);
		renderer3.setHorizontalAlignment( JLabel.CENTER );

		scrollPaneBasket.setViewportView(tableBasket);
		tableModelBasket = new DefaultTableModel(null,columnNamesBasket) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		tableBasket.setModel(tableModelBasket);
		tableBasket.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableBasket.getColumnModel().getColumn(1).setPreferredWidth(100);

		tableBasket.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		tableBasket.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableBasket.setRowHeight(25);

		DefaultTableCellRenderer renderer4 = (DefaultTableCellRenderer)tableBasket.getDefaultRenderer(Object.class);
		renderer4.setHorizontalAlignment( JLabel.CENTER );


		this.getContentPane().add(scrollPaneEvents, null);

		JButton btnApustuBak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SingleResBet")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApustuBak.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApustuBak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Results.removeAllElements();
				tableModelBasket.setDataVector(null, columnNamesBasket);
				FindQuestionsGUI resbatapustua = new FindQuestionsGUI();
				resbatapustua.setVisible(true);
				dispose();
			}
		});

		img1 = new ImageIcon(this.getClass().getResource("/caution-152926_960_720.png")).getImage().getScaledInstance(34,28, Image.SCALE_SMOOTH);
		label_1.setIcon(new ImageIcon(img1));
		lblNewLabel_1 = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(565, 396, 702, 25);
		lblNewLabel_1.setText("");
		getContentPane().add(lblNewLabel_1);
		btnApustuBak.setBounds(932, 665, 350, 40);
		getContentPane().add(btnApustuBak);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneFees, null);
		this.getContentPane().add(scrollPaneBasket, null);
		JButton button = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				erregistratuta dagoen konprobatzeko
				if(gui.UnRegisteredGUI.erab != null) {
					try {
						String input = (JOptionPane.showInputDialog(null,ResourceBundle.getBundle("Etiquetas").getString("howbet") + " ( betmin: " + sumBetMinOfSuperBet() + " )"));

						//	sartutako dirua
						if(input!=null) {
							inputprice = Float.parseFloat(input);
							if(inputprice<=0) JOptionPane.showMessageDialog(null,"Zenbaki desegokia","ERROR",JOptionPane.ERROR_MESSAGE);
							else {
								int resp = JOptionPane.showConfirmDialog(null, printsummarybet(),ResourceBundle.getBundle("Etiquetas").getString("Warning") , JOptionPane.YES_NO_OPTION);
								if(resp == 0) {
									getBusinessLogic().superApustuaEgin(gui.UnRegisteredGUI.erab, inputprice, Results);
									Results.removeAllElements();
									tableModelBasket.setDataVector(null, columnNamesBasket);

									JOptionPane.showMessageDialog(null,ResourceBundle.getBundle("Etiquetas").getString("betComplete"));
								}
							}
						}
					}
					catch (SuperBetMinimumRes e1) {
						JOptionPane.showMessageDialog(null,e1,"Error",JOptionPane.ERROR_MESSAGE);
					}
					catch (EventFinished e1) {
						JOptionPane.showMessageDialog(null,e1,"Error",JOptionPane.ERROR_MESSAGE);
					}
					catch (java.lang.NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,e1,"Error",JOptionPane.ERROR_MESSAGE);
					}
					catch(NotEnoughMoney e1) {
						JOptionPane.showMessageDialog(null,e1,"Error",JOptionPane.ERROR_MESSAGE);
					}
					catch(betMinimum e1) {
						JOptionPane.showMessageDialog(null,e1 ,"Error",JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPaneBasket.setRowHeaderView(button);

		lblNewLabel.setBounds(527, 428, 46, 14);

		getContentPane().add(lblNewLabel);

		lblSeleccionaLaKuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectionFee")); //$NON-NLS-1$ //$NON-NLS-2$
		lblSeleccionaLaKuota.setForeground(new Color(60, 179, 113));
		lblSeleccionaLaKuota.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionaLaKuota.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSeleccionaLaKuota.setBounds(0, 350, 1294, 20);
		getContentPane().add(lblSeleccionaLaKuota);

		JLabel label = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(0, 0, 1294, 715);
		getContentPane().add(label);

		img = new ImageIcon(this.getClass().getResource("/Apuestas.jpg")).getImage().getScaledInstance(1294,715, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(img));

		//	erregistratuta dagoen konprobatzeko
		if(gui.UnRegisteredGUI.erab == null) {
			lblSeleccionaLaKuota.setForeground(Color.red);
			lblSeleccionaLaKuota.setText(ResourceBundle.getBundle("Etiquetas").getString("needRegistration"));
		}

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		Results.removeAllElements();
		this.setVisible(false);

	}

	private Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	private String printsummarybet() {

		String part1 = String.format("%35s", ResourceBundle.getBundle("Etiquetas").getString("BetSummary")+"\n\n");
		String part2 = String.format("%-1s", ResourceBundle.getBundle("Etiquetas").getString("BetType")+ResourceBundle.getBundle("Etiquetas").getString("MultipleRes")+"\n\n");
		String part3 = String.format("%-1s", ResourceBundle.getBundle("Etiquetas").getString("Results")+": "+Results.size()+"\n");
		String part4 = String.format("%-1s", ResourceBundle.getBundle("Etiquetas").getString("WageredMoney")+": "+inputprice+"\n");
		String part5 = String.format("%-1s", ResourceBundle.getBundle("Etiquetas").getString("betDate")+": "+new Date().toString()+"\n");
		String part6 = String.format("%-1s", ResourceBundle.getBundle("Etiquetas").getString("hopeBet")+": "+irabazia()+"\n");
		String part7 = String.format("%-1s", "( "+ResourceBundle.getBundle("Etiquetas").getString("enhancer")+": "+"X"+Results.size()+" )"+"\n\n");
		String part8 = String.format("%40s", ResourceBundle.getBundle("Etiquetas").getString("continue?"));
		return part1+part2+part3+part4+part5+part6+part7+part8;
	}

	private float irabazia() {
		float irabazia = 0;
		for(Result re: Results)
			irabazia+=re.getFee()*inputprice;

		return (float) (irabazia*Results.size());
	}
	private boolean isunlogicalbet() {
		boolean zerochance=false;
		for(Result ri: Results) {
			for(Result rj: Results) {
				Question q1 = getBusinessLogic().getResultContainer(ri).getQuestion();
				Question q2 = getBusinessLogic().getResultContainer(rj).getQuestion();
				if(ri != rj && (q1.getQuestionNumber()) == (q2.getQuestionNumber()))
					zerochance=true;
			}
		}
		return zerochance;
	}
	private float sumBetMinOfSuperBet() {
		float sum = 0;
		for (Result r : Results)
			sum += getBusinessLogic().getResultContainer(r).getQuestion().getBetMinimum();
		return sum;
	}

}

