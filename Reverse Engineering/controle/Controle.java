/*      */ package br.com.schneider.sgm.controle;
/*      */ 
/*      */ import br.com.schneider.sgm.comunicacao.Communication;
/*      */ import br.com.schneider.sgm.config.BuscaParametrosXML;
/*      */ import br.com.schneider.sgm.config.PathConfig;
/*      */ import br.com.schneider.sgm.dispositivos.BZ1500;
/*      */ import br.com.schneider.sgm.dispositivos.PS2200;
/*      */ import br.com.schneider.sgm.dispositivos.PS2200_22;
/*      */ import br.com.schneider.sgm.dispositivos.PS350_CII;
/*      */ import br.com.schneider.sgm.dispositivos.PS800;
/*      */ import br.com.schneider.sgm.dispositivos.Rhino;
/*      */ import br.com.schneider.sgm.dispositivos.SolisDCM15;
/*      */ import br.com.schneider.sgm.dispositivos.SolisLI700;
/*      */ import br.com.schneider.sgm.dispositivos.SolisM11;
/*      */ import br.com.schneider.sgm.dispositivos.SolisM13;
/*      */ import br.com.schneider.sgm.dispositivos.SolisM14;
/*      */ import br.com.schneider.sgm.dispositivos.SolisM15;
/*      */ import br.com.schneider.sgm.dispositivos.Stay1200_USB;
/*      */ import br.com.schneider.sgm.dispositivos.Stay700_USB;
/*      */ import br.com.schneider.sgm.eventos.BatTesteListener;
/*      */ import br.com.schneider.sgm.eventos.ControleListener;
/*      */ import br.com.schneider.sgm.eventos.Evento;
/*      */ import br.com.schneider.sgm.eventos.ProtocoloListener;
/*      */ import br.com.schneider.sgm.eventos.UPSListener;
/*      */ import br.com.schneider.sgm.gui.InterfaceGrafica;
/*      */ import br.com.schneider.sgm.historico.Historico;
/*      */ import br.com.schneider.sgm.historico.HistoricoConsumo;
/*      */ import br.com.schneider.sgm.internacionalizacao.Idioma;
/*      */ import br.com.schneider.sgm.log.LogControl;
/*      */ import br.com.schneider.sgm.persistencia.Persistencia;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloPS;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloRhino;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloSolis;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*      */ import br.com.schneider.sgm.rmi.ClienteRMI;
/*      */ import br.com.schneider.sgm.rmi.servicoso.ServicoWin;
/*      */ import br.com.schneider.sgm.serial.DriverSerial;
/*      */ import br.com.schneider.sgm.servico.BatTestHandler;
/*      */ import br.com.schneider.sgm.servico.ExecutaAcaoRede;
/*      */ import br.com.schneider.sgm.servico.Mensagens;
/*      */ import br.com.schneider.sgm.servico.SalvaConsumo;
/*      */ import br.com.schneider.sgm.servico.SalvaEventos;
/*      */ import br.com.schneider.sgm.snmp.AgenteSGM;
/*      */ import br.com.schneider.sgm.ups.AbstractUPS;
/*      */ import br.com.schneider.sgm.ups.UPSDataObject;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.net.InetAddress;
/*      */ import java.net.UnknownHostException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Vector;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.Timer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Controle
/*      */   implements ProtocoloListener, UPSListener, ActionListener, BatTesteListener
/*      */ {
/*      */   private static final String FILENAME_CONSUMO_XML = "consumo.xml";
/*      */   private static final String FILENAME_EVENTOS_XML = "eventos.xml";
/*      */   private static final String FILENAME_ALARMES_XML = "alarmes.xml";
/*      */   public static final int SOLIS = 1;
/*      */   public static final int RHINO = 2;
/*      */   public static final int STAY = 3;
/*      */   public static final int SOLIS_LI_700 = 169;
/*      */   public static final int SOLIS_M11 = 171;
/*      */   public static final int SOLIS_M15 = 175;
/*      */   public static final int SOLIS_M14 = 174;
/*      */   public static final int SOLIS_M13 = 173;
/*      */   public static final int SOLISDC_M14 = 201;
/*      */   public static final int SOLISDC_M13 = 206;
/*      */   public static final int SOLISDC_M15 = 207;
/*      */   public static final int CABECALHO_RHINO = 194;
/*      */   public static final int PS800 = 185;
/*      */   public static final int STAY1200_USB = 186;
/*      */   public static final int PS350_CII = 184;
/*      */   public static final int PS2200 = 187;
/*      */   public static final int PS2200_22 = 188;
/*      */   public static final int STAY700_USB = 189;
/*      */   public static final int BZ1500 = 190;
/*      */   private static final String version = "4.0";
/*      */   private int familiaUPS;
/*      */   private int modeloUPS;
/*      */   private ProtocoloUPS protocolo;
/*      */   private String nomeUPS;
/*      */   private String nomePCUPS;
/*      */   private boolean falhaCom;
/*      */   private boolean temperaturaElevada;
/*      */   private boolean usandoSomenteBateria;
/*      */   private boolean cargaElevada;
/*      */   private boolean bateriaBaixa;
/*      */   private boolean redeFalha;
/*  211 */   protected boolean primeiraExecucao = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean flagShutFimAut;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  221 */   private boolean shutdownTempoFalha = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private AbstractUPS ups;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int eventoGatilho;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String caminhoScript;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  243 */   private DecimalFormat formatador = new DecimalFormat("00");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int autonomiaMinima;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int expansorBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  258 */   private int tempoFalha = 50000;
/*      */   
/*      */ 
/*      */ 
/*      */   private String idioma;
/*      */   
/*      */ 
/*      */ 
/*      */   private String porta;
/*      */   
/*      */ 
/*      */ 
/*      */   private String[] destinatarios;
/*      */   
/*      */ 
/*      */ 
/*      */   private String remetente;
/*      */   
/*      */ 
/*      */ 
/*      */   private String enderecoSMTP;
/*      */   
/*      */ 
/*      */ 
/*      */   private int portaSMTP;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autenticacao;
/*      */   
/*      */ 
/*      */ 
/*      */   private String usuario;
/*      */   
/*      */ 
/*      */ 
/*      */   private String senha;
/*      */   
/*      */ 
/*      */ 
/*      */   private HistoricoConsumo histCos;
/*      */   
/*      */ 
/*      */ 
/*      */   private Evento[] eventos;
/*      */   
/*      */ 
/*      */ 
/*      */   private LogControl valuesLog;
/*      */   
/*      */ 
/*      */ 
/*  310 */   private Vector<Evento> vetorEventos = new Vector();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean[][] mensagens;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int horaDesligar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int minutoDesligar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int horaLigar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int minutoLigar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int horaDesligarAgendada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int minutoDesligarAgendado;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  355 */   private boolean[] programacao = new boolean[7];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Timer timerCom;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  367 */   private int delayTimerCom = 500000;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Timer timerShut;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Timer timerFalha;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String sistemaOperacional;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Persistencia persistencia;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Communication driver;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private InterfaceGrafica gui;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private ControleListener[] ouvintesControle;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private BuscaParametrosXML parametrosXML;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private AgenteSGM agenteSNMP;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String enderecoIPLocal;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Historico historicoEventos;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int contConsumo;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private BatTestHandler autoTesteHandler;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private float consumo;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean flagShutdown;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ExecutaAcaoRede executaAcaoRede;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  458 */   private boolean bateriaBaixaScript = false;
/*      */   
/*      */ 
/*  461 */   private boolean execucaoViaPrompt = false;
/*      */   
/*      */ 
/*  464 */   protected String caminhoAppDataService = "";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Controle()
/*      */   {
/*  478 */     initControle();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Controle(String caminhoAppDataService)
/*      */   {
/*  486 */     this.caminhoAppDataService = caminhoAppDataService;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void initControle()
/*      */   {
/*  496 */     this.sistemaOperacional = System.getProperty("os.name").toLowerCase();
/*      */     
/*  498 */     this.contConsumo = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  503 */     if (isGui())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  508 */       if (this.sistemaOperacional.indexOf("windows") >= 0) {
/*  509 */         new ServicoWin().parar();
/*      */       } else {
/*  511 */         new ClienteRMI().fecharPortaUsbUtilizadaPeloServico();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  520 */     if (isServicoSO()) {
/*  521 */       PathConfig.setPath(this.caminhoAppDataService);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  529 */     this.historicoEventos = new Historico(PathConfig.getPathXML() + "eventos.xml");
/*  530 */     this.histCos = new HistoricoConsumo(PathConfig.getPathXML() + "consumo.xml");
/*      */     
/*  532 */     this.flagShutdown = false;
/*      */     
/*      */ 
/*      */ 
/*  536 */     this.driver = new DriverSerial();
/*      */     
/*      */ 
/*  539 */     this.timerCom = new Timer(this.delayTimerCom, this);
/*      */     
/*      */ 
/*      */ 
/*  543 */     this.timerShut = new Timer(1000, this);
/*      */     
/*      */ 
/*      */ 
/*  547 */     this.timerFalha = new Timer(this.tempoFalha, this);
/*      */     
/*      */ 
/*  550 */     this.parametrosXML = new BuscaParametrosXML();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  559 */     this.persistencia = new Persistencia(PathConfig.getPathXML());
/*      */     
/*      */ 
/*      */ 
/*  563 */     Evento[] evts = importArrayEvento();
/*  564 */     if (evts != null) {
/*  565 */       putEventos(importArrayEvento());
/*      */     }
/*      */     else {
/*  568 */       Calendar calendar = new GregorianCalendar();
/*  569 */       this.historicoEventos.criaArquivo();
/*  570 */       Evento evt = new Evento("Ok", 
/*  571 */         calendar.get(11), 
/*  572 */         calendar.get(12), calendar.get(13), 
/*  573 */         calendar.get(5), 
/*  574 */         calendar.get(2) + 1, calendar.get(1));
/*  575 */       putEventos(evt);
/*  576 */       Evento[] evtss = { evt };
/*  577 */       salvaEventos(evtss);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  597 */     this.valuesLog = new LogControl(this);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  602 */     if (isGui()) {
/*  603 */       this.gui = new InterfaceGrafica("Figuras" + File.separator, this, getPortas());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  608 */     this.ouvintesControle = new ControleListener[10];
/*      */     
/*      */ 
/*  611 */     this.autoTesteHandler = new BatTestHandler(this, this);
/*  612 */     this.autoTesteHandler.setAutoTesteHabilitado(this.persistencia.getAutoTesteEnabled());
/*  613 */     this.autoTesteHandler.setHoraTeste(this.persistencia.getHoraTeste());
/*  614 */     this.autoTesteHandler.setMinutoTeste(this.persistencia.getMinutoTeste());
/*  615 */     this.autoTesteHandler.setPeriodoAutoTeste(this.persistencia.getPeriodoTeste());
/*  616 */     Thread threadAutoTesteHandler = new Thread(this.autoTesteHandler);
/*  617 */     threadAutoTesteHandler.start();
/*      */     
/*      */ 
/*      */ 
/*  621 */     if (isGui()) {
/*  622 */       addControleListener(this.gui);
/*      */     }
/*  624 */     addControleListener(this.valuesLog);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  631 */     this.parametrosXML.setNomeDocumento(PathConfig.getPathXML() + "alarmes.xml");
/*      */     
/*      */ 
/*      */ 
/*  635 */     Evento.listaAlarmes = this.parametrosXML.configuraAlarmes();
/*      */     
/*      */ 
/*      */ 
/*  639 */     if (isGui()) {
/*  640 */       this.gui.setEventos(Evento.listaAlarmes);
/*      */     }
/*  642 */     if (isGui()) {
/*  643 */       this.gui.lePersistencia();
/*      */     }
/*  645 */     if (isServicoSO()) {
/*  646 */       lePersistenciaComDadosUteisParaOservico();
/*      */     }
/*      */     
/*  649 */     this.valuesLog.setLogEnabled(this.persistencia.getModoLogging());
/*      */     
/*  651 */     this.programacao = new boolean[7];
/*      */     
/*  653 */     if (isModoRemoto())
/*      */     {
/*  655 */       this.executaAcaoRede = new ExecutaAcaoRede();
/*  656 */       this.executaAcaoRede.setPortaEscuta(getPortaRemota());
/*  657 */       this.executaAcaoRede.start();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  663 */     if (isGui()) {
/*      */       try {
/*  665 */         this.enderecoIPLocal = InetAddress.getLocalHost().getHostAddress();
/*      */         try
/*      */         {
/*  668 */           this.agenteSNMP = new AgenteSGM(new File("AgenteSGMBC.cfg"), new File("AgenteSGMConfig.cfg"), 
/*  669 */             this.enderecoIPLocal + "/" + getPortaPedidos(), this, this.histCos);
/*      */           
/*  671 */           this.agenteSNMP.init();
/*  672 */           this.agenteSNMP.loadConfig(2);
/*  673 */           this.agenteSNMP.run();
/*  674 */           this.agenteSNMP.getTimerPerMIB().start();
/*  675 */           addControleListener(this.agenteSNMP);
/*      */         } catch (IOException ex) {
/*  677 */           System.err.println(ex.getMessage());
/*  678 */           ex.printStackTrace();
/*      */         } catch (Exception e) {
/*  680 */           System.err.println(e.getMessage());
/*  681 */           e.printStackTrace();
/*      */         }
/*      */         return;
/*      */       }
/*      */       catch (UnknownHostException uhe)
/*      */       {
/*  687 */         System.err.println(uhe.getMessage());
/*  688 */         uhe.printStackTrace();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isGui()
/*      */   {
/*  700 */     return !this.execucaoViaPrompt;
/*      */   }
/*      */   
/*      */   protected boolean isServicoSO() {
/*  704 */     return this.execucaoViaPrompt;
/*      */   }
/*      */   
/*      */   protected void setServicoSO(boolean execucaoViaPrompt) {
/*  708 */     this.execucaoViaPrompt = execucaoViaPrompt;
/*      */   }
/*      */   
/*      */   private void lePersistenciaComDadosUteisParaOservico()
/*      */   {
/*  713 */     setIdioma((String)this.persistencia.getIdioma());
/*  714 */     if (getIdioma().toUpperCase().startsWith("PORTUGU")) {
/*  715 */       Idioma.setIdiomaPortugues();
/*      */     } else {
/*  717 */       Idioma.setIdiomaIngles();
/*      */     }
/*  719 */     setAutonomiaMinima(this.persistencia.getAutonoMinima());
/*  720 */     setFlagShutFimAut(this.persistencia.isFlagShutFimAut());
/*      */     
/*  722 */     setTempoFalha(this.persistencia.getTempoFalhaEletrica());
/*  723 */     setShutdownTempoFalha(this.persistencia.isFlagShutFalhaEletrica());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void pedidoDumpping()
/*      */   {
/*  732 */     this.protocolo.pedidoDumpping();
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public void setLocalAdress(String local)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   4: invokevirtual 595	br/com/schneider/sgm/snmp/AgenteSGM:getSession	()Lorg/snmp4j/Snmp;
/*      */     //   7: invokevirtual 599	org/snmp4j/Snmp:close	()V
/*      */     //   10: aload_0
/*      */     //   11: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   14: aload_1
/*      */     //   15: invokevirtual 604	br/com/schneider/sgm/snmp/AgenteSGM:setLocalAddress	(Ljava/lang/String;)V
/*      */     //   18: aload_0
/*      */     //   19: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   22: invokevirtual 490	br/com/schneider/sgm/snmp/AgenteSGM:getTimerPerMIB	()Ljavax/swing/Timer;
/*      */     //   25: invokevirtual 607	javax/swing/Timer:stop	()V
/*      */     //   28: aload_0
/*      */     //   29: aconst_null
/*      */     //   30: putfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   33: goto +328 -> 361
/*      */     //   36: astore_2
/*      */     //   37: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   40: aload_2
/*      */     //   41: invokevirtual 512	java/lang/Exception:getMessage	()Ljava/lang/String;
/*      */     //   44: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   47: aload_2
/*      */     //   48: invokevirtual 515	java/lang/Exception:printStackTrace	()V
/*      */     //   51: new 461	br/com/schneider/sgm/snmp/AgenteSGM
/*      */     //   54: dup
/*      */     //   55: new 347	java/io/File
/*      */     //   58: dup
/*      */     //   59: ldc_w 463
/*      */     //   62: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   65: new 347	java/io/File
/*      */     //   68: dup
/*      */     //   69: ldc_w 466
/*      */     //   72: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   75: new 249	java/lang/StringBuilder
/*      */     //   78: dup
/*      */     //   79: aload_0
/*      */     //   80: getfield 459	br/com/schneider/sgm/controle/Controle:enderecoIPLocal	Ljava/lang/String;
/*      */     //   83: invokestatic 254	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   86: invokespecial 258	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   89: ldc_w 468
/*      */     //   92: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   95: aload_0
/*      */     //   96: invokevirtual 470	br/com/schneider/sgm/controle/Controle:getPortaPedidos	()I
/*      */     //   99: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*      */     //   102: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   105: aload_0
/*      */     //   106: aload_0
/*      */     //   107: getfield 272	br/com/schneider/sgm/controle/Controle:histCos	Lbr/com/schneider/sgm/historico/HistoricoConsumo;
/*      */     //   110: invokespecial 476	br/com/schneider/sgm/snmp/AgenteSGM:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;Lbr/com/schneider/sgm/controle/Controle;Lbr/com/schneider/sgm/historico/HistoricoConsumo;)V
/*      */     //   113: astore 4
/*      */     //   115: aload_0
/*      */     //   116: aload 4
/*      */     //   118: putfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   121: aload_0
/*      */     //   122: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   125: invokevirtual 481	br/com/schneider/sgm/snmp/AgenteSGM:init	()V
/*      */     //   128: aload_0
/*      */     //   129: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   132: iconst_2
/*      */     //   133: invokevirtual 484	br/com/schneider/sgm/snmp/AgenteSGM:loadConfig	(I)V
/*      */     //   136: aload_0
/*      */     //   137: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   140: invokevirtual 487	br/com/schneider/sgm/snmp/AgenteSGM:run	()V
/*      */     //   143: aload_0
/*      */     //   144: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   147: invokevirtual 490	br/com/schneider/sgm/snmp/AgenteSGM:getTimerPerMIB	()Ljavax/swing/Timer;
/*      */     //   150: invokevirtual 494	javax/swing/Timer:start	()V
/*      */     //   153: aload_0
/*      */     //   154: aload_0
/*      */     //   155: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   158: invokevirtual 406	br/com/schneider/sgm/controle/Controle:addControleListener	(Lbr/com/schneider/sgm/eventos/ControleListener;)V
/*      */     //   161: goto +352 -> 513
/*      */     //   164: astore 4
/*      */     //   166: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   169: aload 4
/*      */     //   171: invokevirtual 499	java/io/IOException:getMessage	()Ljava/lang/String;
/*      */     //   174: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   177: aload 4
/*      */     //   179: invokevirtual 509	java/io/IOException:printStackTrace	()V
/*      */     //   182: goto +331 -> 513
/*      */     //   185: astore 4
/*      */     //   187: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   190: aload 4
/*      */     //   192: invokevirtual 512	java/lang/Exception:getMessage	()Ljava/lang/String;
/*      */     //   195: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   198: aload 4
/*      */     //   200: invokevirtual 515	java/lang/Exception:printStackTrace	()V
/*      */     //   203: goto +310 -> 513
/*      */     //   206: astore_3
/*      */     //   207: new 461	br/com/schneider/sgm/snmp/AgenteSGM
/*      */     //   210: dup
/*      */     //   211: new 347	java/io/File
/*      */     //   214: dup
/*      */     //   215: ldc_w 463
/*      */     //   218: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   221: new 347	java/io/File
/*      */     //   224: dup
/*      */     //   225: ldc_w 466
/*      */     //   228: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   231: new 249	java/lang/StringBuilder
/*      */     //   234: dup
/*      */     //   235: aload_0
/*      */     //   236: getfield 459	br/com/schneider/sgm/controle/Controle:enderecoIPLocal	Ljava/lang/String;
/*      */     //   239: invokestatic 254	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   242: invokespecial 258	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   245: ldc_w 468
/*      */     //   248: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   251: aload_0
/*      */     //   252: invokevirtual 470	br/com/schneider/sgm/controle/Controle:getPortaPedidos	()I
/*      */     //   255: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*      */     //   258: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   261: aload_0
/*      */     //   262: aload_0
/*      */     //   263: getfield 272	br/com/schneider/sgm/controle/Controle:histCos	Lbr/com/schneider/sgm/historico/HistoricoConsumo;
/*      */     //   266: invokespecial 476	br/com/schneider/sgm/snmp/AgenteSGM:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;Lbr/com/schneider/sgm/controle/Controle;Lbr/com/schneider/sgm/historico/HistoricoConsumo;)V
/*      */     //   269: astore 4
/*      */     //   271: aload_0
/*      */     //   272: aload 4
/*      */     //   274: putfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   277: aload_0
/*      */     //   278: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   281: invokevirtual 481	br/com/schneider/sgm/snmp/AgenteSGM:init	()V
/*      */     //   284: aload_0
/*      */     //   285: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   288: iconst_2
/*      */     //   289: invokevirtual 484	br/com/schneider/sgm/snmp/AgenteSGM:loadConfig	(I)V
/*      */     //   292: aload_0
/*      */     //   293: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   296: invokevirtual 487	br/com/schneider/sgm/snmp/AgenteSGM:run	()V
/*      */     //   299: aload_0
/*      */     //   300: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   303: invokevirtual 490	br/com/schneider/sgm/snmp/AgenteSGM:getTimerPerMIB	()Ljavax/swing/Timer;
/*      */     //   306: invokevirtual 494	javax/swing/Timer:start	()V
/*      */     //   309: aload_0
/*      */     //   310: aload_0
/*      */     //   311: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   314: invokevirtual 406	br/com/schneider/sgm/controle/Controle:addControleListener	(Lbr/com/schneider/sgm/eventos/ControleListener;)V
/*      */     //   317: goto +42 -> 359
/*      */     //   320: astore 4
/*      */     //   322: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   325: aload 4
/*      */     //   327: invokevirtual 499	java/io/IOException:getMessage	()Ljava/lang/String;
/*      */     //   330: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   333: aload 4
/*      */     //   335: invokevirtual 509	java/io/IOException:printStackTrace	()V
/*      */     //   338: goto +21 -> 359
/*      */     //   341: astore 4
/*      */     //   343: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   346: aload 4
/*      */     //   348: invokevirtual 512	java/lang/Exception:getMessage	()Ljava/lang/String;
/*      */     //   351: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   354: aload 4
/*      */     //   356: invokevirtual 515	java/lang/Exception:printStackTrace	()V
/*      */     //   359: aload_3
/*      */     //   360: athrow
/*      */     //   361: new 461	br/com/schneider/sgm/snmp/AgenteSGM
/*      */     //   364: dup
/*      */     //   365: new 347	java/io/File
/*      */     //   368: dup
/*      */     //   369: ldc_w 463
/*      */     //   372: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   375: new 347	java/io/File
/*      */     //   378: dup
/*      */     //   379: ldc_w 466
/*      */     //   382: invokespecial 465	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   385: new 249	java/lang/StringBuilder
/*      */     //   388: dup
/*      */     //   389: aload_0
/*      */     //   390: getfield 459	br/com/schneider/sgm/controle/Controle:enderecoIPLocal	Ljava/lang/String;
/*      */     //   393: invokestatic 254	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   396: invokespecial 258	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   399: ldc_w 468
/*      */     //   402: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   405: aload_0
/*      */     //   406: invokevirtual 470	br/com/schneider/sgm/controle/Controle:getPortaPedidos	()I
/*      */     //   409: invokevirtual 473	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*      */     //   412: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   415: aload_0
/*      */     //   416: aload_0
/*      */     //   417: getfield 272	br/com/schneider/sgm/controle/Controle:histCos	Lbr/com/schneider/sgm/historico/HistoricoConsumo;
/*      */     //   420: invokespecial 476	br/com/schneider/sgm/snmp/AgenteSGM:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;Lbr/com/schneider/sgm/controle/Controle;Lbr/com/schneider/sgm/historico/HistoricoConsumo;)V
/*      */     //   423: astore 4
/*      */     //   425: aload_0
/*      */     //   426: aload 4
/*      */     //   428: putfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   431: aload_0
/*      */     //   432: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   435: invokevirtual 481	br/com/schneider/sgm/snmp/AgenteSGM:init	()V
/*      */     //   438: aload_0
/*      */     //   439: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   442: iconst_2
/*      */     //   443: invokevirtual 484	br/com/schneider/sgm/snmp/AgenteSGM:loadConfig	(I)V
/*      */     //   446: aload_0
/*      */     //   447: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   450: invokevirtual 487	br/com/schneider/sgm/snmp/AgenteSGM:run	()V
/*      */     //   453: aload_0
/*      */     //   454: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   457: invokevirtual 490	br/com/schneider/sgm/snmp/AgenteSGM:getTimerPerMIB	()Ljavax/swing/Timer;
/*      */     //   460: invokevirtual 494	javax/swing/Timer:start	()V
/*      */     //   463: aload_0
/*      */     //   464: aload_0
/*      */     //   465: getfield 479	br/com/schneider/sgm/controle/Controle:agenteSNMP	Lbr/com/schneider/sgm/snmp/AgenteSGM;
/*      */     //   468: invokevirtual 406	br/com/schneider/sgm/controle/Controle:addControleListener	(Lbr/com/schneider/sgm/eventos/ControleListener;)V
/*      */     //   471: goto +42 -> 513
/*      */     //   474: astore 4
/*      */     //   476: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   479: aload 4
/*      */     //   481: invokevirtual 499	java/io/IOException:getMessage	()Ljava/lang/String;
/*      */     //   484: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   487: aload 4
/*      */     //   489: invokevirtual 509	java/io/IOException:printStackTrace	()V
/*      */     //   492: goto +21 -> 513
/*      */     //   495: astore 4
/*      */     //   497: getstatic 495	java/lang/System:err	Ljava/io/PrintStream;
/*      */     //   500: aload 4
/*      */     //   502: invokevirtual 512	java/lang/Exception:getMessage	()Ljava/lang/String;
/*      */     //   505: invokevirtual 504	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   508: aload 4
/*      */     //   510: invokevirtual 515	java/lang/Exception:printStackTrace	()V
/*      */     //   513: return
/*      */     // Line number table:
/*      */     //   Java source line #742	-> byte code offset #0
/*      */     //   Java source line #743	-> byte code offset #10
/*      */     //   Java source line #744	-> byte code offset #18
/*      */     //   Java source line #746	-> byte code offset #28
/*      */     //   Java source line #748	-> byte code offset #33
/*      */     //   Java source line #749	-> byte code offset #36
/*      */     //   Java source line #750	-> byte code offset #37
/*      */     //   Java source line #751	-> byte code offset #47
/*      */     //   Java source line #758	-> byte code offset #51
/*      */     //   Java source line #759	-> byte code offset #75
/*      */     //   Java source line #758	-> byte code offset #110
/*      */     //   Java source line #760	-> byte code offset #115
/*      */     //   Java source line #761	-> byte code offset #121
/*      */     //   Java source line #762	-> byte code offset #128
/*      */     //   Java source line #763	-> byte code offset #136
/*      */     //   Java source line #764	-> byte code offset #143
/*      */     //   Java source line #766	-> byte code offset #153
/*      */     //   Java source line #769	-> byte code offset #161
/*      */     //   Java source line #770	-> byte code offset #166
/*      */     //   Java source line #771	-> byte code offset #177
/*      */     //   Java source line #772	-> byte code offset #185
/*      */     //   Java source line #773	-> byte code offset #187
/*      */     //   Java source line #774	-> byte code offset #198
/*      */     //   Java source line #753	-> byte code offset #206
/*      */     //   Java source line #758	-> byte code offset #207
/*      */     //   Java source line #759	-> byte code offset #231
/*      */     //   Java source line #758	-> byte code offset #266
/*      */     //   Java source line #760	-> byte code offset #271
/*      */     //   Java source line #761	-> byte code offset #277
/*      */     //   Java source line #762	-> byte code offset #284
/*      */     //   Java source line #763	-> byte code offset #292
/*      */     //   Java source line #764	-> byte code offset #299
/*      */     //   Java source line #766	-> byte code offset #309
/*      */     //   Java source line #769	-> byte code offset #317
/*      */     //   Java source line #770	-> byte code offset #322
/*      */     //   Java source line #771	-> byte code offset #333
/*      */     //   Java source line #772	-> byte code offset #341
/*      */     //   Java source line #773	-> byte code offset #343
/*      */     //   Java source line #774	-> byte code offset #354
/*      */     //   Java source line #776	-> byte code offset #359
/*      */     //   Java source line #758	-> byte code offset #361
/*      */     //   Java source line #759	-> byte code offset #385
/*      */     //   Java source line #758	-> byte code offset #420
/*      */     //   Java source line #760	-> byte code offset #425
/*      */     //   Java source line #761	-> byte code offset #431
/*      */     //   Java source line #762	-> byte code offset #438
/*      */     //   Java source line #763	-> byte code offset #446
/*      */     //   Java source line #764	-> byte code offset #453
/*      */     //   Java source line #766	-> byte code offset #463
/*      */     //   Java source line #769	-> byte code offset #471
/*      */     //   Java source line #770	-> byte code offset #476
/*      */     //   Java source line #771	-> byte code offset #487
/*      */     //   Java source line #772	-> byte code offset #495
/*      */     //   Java source line #773	-> byte code offset #497
/*      */     //   Java source line #774	-> byte code offset #508
/*      */     //   Java source line #778	-> byte code offset #513
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	514	0	this	Controle
/*      */     //   0	514	1	local	String
/*      */     //   36	12	2	e	Exception
/*      */     //   206	154	3	localObject	Object
/*      */     //   113	4	4	agente	AgenteSGM
/*      */     //   164	14	4	ex	IOException
/*      */     //   185	14	4	e	Exception
/*      */     //   269	4	4	agente	AgenteSGM
/*      */     //   320	14	4	ex	IOException
/*      */     //   341	14	4	e	Exception
/*      */     //   423	4	4	agente	AgenteSGM
/*      */     //   474	14	4	ex	IOException
/*      */     //   495	14	4	e	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   0	33	36	java/lang/Exception
/*      */     //   51	161	164	java/io/IOException
/*      */     //   51	161	185	java/lang/Exception
/*      */     //   0	51	206	finally
/*      */     //   207	317	320	java/io/IOException
/*      */     //   207	317	341	java/lang/Exception
/*      */     //   361	471	474	java/io/IOException
/*      */     //   361	471	495	java/lang/Exception
/*      */   }
/*      */   
/*      */   public boolean setComunidadeLeitura(String nova, String antiga)
/*      */   {
/*  785 */     return this.agenteSNMP.changeReadOnlyComunityString(antiga, nova);
/*      */   }
/*      */   
/*      */   public boolean setConfigTraps(String ipGerente, int portaEnvio, int versao, String protocoloTransporte, String comEscrita)
/*      */   {
/*  790 */     return this.agenteSNMP.changeConfigTraps(ipGerente, portaEnvio, versao, protocoloTransporte, comEscrita);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean setComunidadeEscrita(String nova, String antiga)
/*      */   {
/*  798 */     return this.agenteSNMP.changeReadWriteComunityString(antiga, nova);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addControleListener(ControleListener ouvinteControle)
/*      */   {
/*  807 */     int i = 0;
/*      */     
/*  809 */     while ((this.ouvintesControle[i] != null) && (i < this.ouvintesControle.length)) {
/*  810 */       i++;
/*      */     }
/*  812 */     this.ouvintesControle[i] = ouvinteControle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notificaOuvintesControle(int tipoNotificacao)
/*      */   {
/*  822 */     int i = 0;
/*  823 */     while ((this.ouvintesControle[i] != null) && (i < this.ouvintesControle.length))
/*      */     {
/*  825 */       switch (tipoNotificacao) {
/*      */       case 1: 
/*  827 */         this.ouvintesControle[i].notificaDados();
/*  828 */         break;
/*      */       case 2: 
/*  830 */         this.ouvintesControle[i].notificaFalhaCom();
/*  831 */         break;
/*      */       case 3: 
/*  833 */         this.ouvintesControle[i].notificaRetornoCom();
/*  834 */         break;
/*      */       case 4: 
/*  836 */         this.ouvintesControle[i].notificaFalhaRede();
/*  837 */         break;
/*      */       case 5: 
/*  839 */         this.ouvintesControle[i].notificaRetornoRede();
/*  840 */         break;
/*      */       case 6: 
/*  842 */         this.ouvintesControle[i].notificaBateriaBaixa();
/*  843 */         if (!this.bateriaBaixaScript) {
/*  844 */           this.bateriaBaixaScript = true;
/*  845 */           verificaArquivoLote(new Evento[] { new Evento("BATERIA_BAIXA", 0, 0, 0, 0, 0, 0) });
/*      */         }
/*  847 */         break;
/*      */       case 7: 
/*  849 */         this.ouvintesControle[i].notificaBateriaNormal();
/*  850 */         break;
/*      */       case 8: 
/*  852 */         this.ouvintesControle[i].notificaCargaElevada();
/*  853 */         break;
/*      */       case 9: 
/*  855 */         this.ouvintesControle[i].notificaCargaNormal();
/*  856 */         break;
/*      */       case 10: 
/*  858 */         this.ouvintesControle[i].notificaTemperaturaElevada();
/*  859 */         break;
/*      */       case 11: 
/*  861 */         this.ouvintesControle[i].notificaTemperaturaNormal();
/*  862 */         break;
/*      */       case 12: 
/*  864 */         this.ouvintesControle[i].notificaUsandoBateria();
/*  865 */         break;
/*      */       case 13: 
/*  867 */         this.ouvintesControle[i].notificaNaoUsaBateria();
/*  868 */         break;
/*      */       case 14: 
/*  870 */         this.ouvintesControle[i].notificaComunicacao();
/*      */       }
/*      */       
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  877 */       i++;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getVersaoSoftware()
/*      */   {
/*  890 */     return "4.0";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraUPSEtapa1(int familiaUPS, String porta)
/*      */   {
/*  900 */     if (!setFamiliaUPS(familiaUPS)) {
/*  901 */       return false;
/*      */     }
/*  903 */     if ((isServicoSO()) && (porta != null) && 
/*  904 */       (this.sistemaOperacional.indexOf("windows") >= 0)) {
/*  905 */       porta = porta.replaceAll("[^a-zA-Z0-9]", "");
/*  906 */       porta = porta.replaceAll("/", "");
/*  907 */       porta = porta.replace(".", "");
/*      */     }
/*      */     
/*      */ 
/*  911 */     if (!setPorta(porta)) {
/*  912 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  916 */     if (!this.timerCom.isRunning()) {
/*  917 */       this.timerCom.start();
/*      */     } else {
/*  919 */       this.timerCom.restart();
/*      */     }
/*  921 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraUPSEtapa2()
/*      */   {
/*  933 */     this.modeloUPS = this.protocolo.getCabecalhoPacote();
/*      */     
/*  935 */     switch (this.modeloUPS) {
/*      */     case 194: 
/*  937 */       this.ups = new Rhino(this.protocolo);
/*      */       
/*  939 */       break;
/*      */     
/*      */     case 201: 
/*      */     case 206: 
/*      */     case 207: 
/*  944 */       this.ups = new SolisDCM15(this.protocolo);
/*  945 */       break;
/*      */     case 173: 
/*  947 */       this.ups = new SolisM13(this.protocolo);
/*  948 */       break;
/*      */     
/*      */     case 174: 
/*  951 */       this.ups = new SolisM14(this.protocolo);
/*  952 */       break;
/*      */     
/*      */     case 175: 
/*  955 */       this.ups = new SolisM15(this.protocolo);
/*  956 */       break;
/*      */     case 171: 
/*  958 */       this.ups = new SolisM11(this.protocolo);
/*  959 */       break;
/*      */     
/*      */     case 169: 
/*  962 */       this.ups = new SolisLI700(this.protocolo);
/*  963 */       break;
/*      */     
/*      */ 
/*      */     case 185: 
/*  967 */       this.ups = new PS800((ProtocoloPS)this.protocolo);
/*  968 */       break;
/*      */     
/*      */ 
/*      */     case 184: 
/*  972 */       this.ups = new PS350_CII((ProtocoloPS)this.protocolo);
/*  973 */       break;
/*      */     
/*      */ 
/*      */     case 186: 
/*  977 */       this.ups = new Stay1200_USB((ProtocoloPS)this.protocolo);
/*  978 */       break;
/*      */     
/*      */     case 187: 
/*  981 */       this.ups = new PS2200((ProtocoloPS)this.protocolo);
/*  982 */       break;
/*      */     
/*      */     case 188: 
/*  985 */       this.ups = new PS2200_22((ProtocoloPS)this.protocolo);
/*  986 */       break;
/*      */     
/*      */     case 189: 
/*  989 */       this.ups = new Stay700_USB((ProtocoloPS)this.protocolo);
/*  990 */       break;
/*      */     
/*      */     case 190: 
/*  993 */       this.ups = new BZ1500((ProtocoloPS)this.protocolo);
/*  994 */       break;
/*      */     case 170: case 172: case 176: 
/*      */     case 177: case 178: case 179: 
/*      */     case 180: case 181: case 182: 
/*      */     case 183: case 191: case 192: 
/*      */     case 193: case 195: case 196: 
/*      */     case 197: case 198: case 199: 
/*      */     case 200: case 202: case 203: 
/*      */     case 204: case 205: default: 
/* 1003 */       this.protocolo.addProtocoloListener(null);
/* 1004 */       if (isGui()) {
/* 1005 */         this.gui.mostraPopUp("Erro_de_configuracao__Verifique_a_familia_de_UPS_selecionada");
/*      */       }
/* 1007 */       return false;
/*      */     }
/*      */     
/*      */     
/* 1011 */     this.protocolo.addProtocoloListener(this.ups);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1016 */     this.ups.addUPSListener(this);
/*      */     
/* 1018 */     this.ups.notifica();
/*      */     
/* 1020 */     this.ups.setExpansorBateria(this.expansorBateria);
/* 1021 */     notificaControle();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1027 */     if (this.modeloUPS != 194) {
/* 1028 */       atualizaCalendarioRelogio();
/*      */     }
/* 1030 */     notificaOuvintesControle(14);
/*      */     
/*      */ 
/*      */ 
/* 1034 */     if (!this.timerShut.isRunning())
/* 1035 */       this.timerShut.start();
/* 1036 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean atualizaCalendarioRelogio()
/*      */   {
/* 1046 */     GregorianCalendar horaAtual = new GregorianCalendar();
/*      */     
/* 1048 */     return this.ups.configuraCalendarioRelogio(horaAtual.get(7) - 1, horaAtual.get(5), 
/* 1049 */       horaAtual.get(2) + 1, horaAtual.get(1), horaAtual.get(13), 
/* 1050 */       horaAtual.get(12), horaAtual.get(11));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void desligaUPS2()
/*      */   {
/* 1058 */     JOptionPane.showMessageDialog(null, "desligaUPS(): " + new Date());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void desligaUPS()
/*      */   {
/* 1068 */     if (this.executaAcaoRede != null) {
/*      */       try {
/* 1070 */         this.executaAcaoRede.encerraClientes();
/*      */       }
/*      */       catch (Exception e) {
/* 1073 */         e.printStackTrace();
/*      */       }
/*      */     }
/*      */     
/* 1077 */     encerraSO();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void religaUPS2()
/*      */   {
/* 1085 */     JOptionPane.showMessageDialog(null, "2 - religaUPS - EXECUTADO!!!!!!!!!!!!!!!!!");
/*      */   }
/*      */   
/*      */   public void religaUPS() {
/* 1089 */     this.ups.shutdownReligamento();
/*      */     try {
/* 1091 */       this.executaAcaoRede.encerraClientes();
/*      */     }
/*      */     catch (Exception e) {
/* 1094 */       e.printStackTrace();
/*      */     }
/* 1096 */     encerraSO();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void encerraSO()
/*      */   {
/* 1105 */     if (this.sistemaOperacional.indexOf("windows") >= 0) {
/*      */       try {
/*      */         try {
/* 1108 */           Runtime.getRuntime().exec("rundll.exe user.exe,ExitWindows");
/*      */         }
/*      */         catch (Exception localException) {}
/*      */         
/* 1112 */         Runtime.getRuntime().exec("shutdown /s /f /t 30 /c \"SGM - Software de Gerenciamento Microsol.\"");
/* 1113 */         this.flagShutdown = true;
/*      */       } catch (IOException ioe) {
/* 1115 */         System.err.println(ioe.getMessage());
/* 1116 */         ioe.printStackTrace();
/*      */       }
/*      */     }
/* 1119 */     if (this.sistemaOperacional.indexOf("linux") >= 0) {
/*      */       try
/*      */       {
/* 1122 */         Runtime.getRuntime().exec("shutdown -h 1 SGM - Software de Gerenciamento Microsol.");
/* 1123 */         this.flagShutdown = true;
/*      */       } catch (IOException ioe) {
/* 1125 */         System.err.println(ioe.getMessage());
/* 1126 */         ioe.printStackTrace();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void notifica()
/*      */   {
/* 1134 */     configuraUPSEtapa2();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void verificaArquivoLote(Evento[] evts)
/*      */   {
/* 1141 */     if (evts != null) {
/* 1142 */       boolean naoOcorreu = true;
/* 1143 */       int i = 0;
/* 1144 */       setEventoGatilho(getEventoPersistencia());
/* 1145 */       while ((naoOcorreu) && (evts[i] != null))
/*      */       {
/* 1147 */         if ((evts[i].getTipo().equals(Evento.listaAlarmes[this.eventoGatilho])) || (
/* 1148 */           ((evts[i].getTipo().equalsIgnoreCase("SUBTENSAO_ENTRADA")) || (evts[i].getTipo().equalsIgnoreCase("SOBRETENSAO_ENTRADA"))) && 
/* 1149 */           (Evento.listaAlarmes[this.eventoGatilho].equalsIgnoreCase("FALHA_NA_REDE")))) {
/* 1150 */           naoOcorreu = false;
/*      */           
/* 1152 */           if (this.persistencia.getScript() != null) {
/* 1153 */             executaScript();
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1159 */         i++;
/* 1160 */         if (i == evts.length) {
/* 1161 */           naoOcorreu = false;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void executaScript()
/*      */   {
/* 1173 */     if (this.sistemaOperacional.indexOf("windows") >= 0)
/*      */     {
/*      */       try {
/* 1176 */         Runtime.getRuntime().exec("cmd /c \"" + this.persistencia.getScript() + "\"");
/*      */       } catch (IOException ioe) {
/* 1178 */         System.err.println(ioe.getMessage());
/* 1179 */         ioe.printStackTrace();
/* 1180 */         if (!isGui()) return; }
/* 1181 */       this.gui.mostraPopUp("Erro_executar_script");
/*      */ 
/*      */     }
/* 1184 */     else if (this.sistemaOperacional.indexOf("linux") >= 0)
/*      */     {
/*      */       try {
/* 1187 */         Runtime.getRuntime().exec(this.persistencia.getScript());
/*      */       } catch (IOException ioe) {
/* 1189 */         System.err.println(ioe.getMessage());
/* 1190 */         ioe.printStackTrace();
/* 1191 */         if (isGui()) {
/* 1192 */           this.gui.mostraPopUp("Erro_executar_script");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enviaEmail(String mensagem)
/*      */   {
/* 1210 */     Mensagens email = new Mensagens(mensagem, this.persistencia.getNomeUPS(), this.formatador.format(this.ups.getHora()) + ":" + this.formatador.format(this.ups.getMinutos()) + 
/* 1211 */       ":" + this.formatador.format(this.ups.getSegundos()), 
/* 1212 */       this.persistencia.getDestinatarios(), this.persistencia.getRemetente(), this.persistencia.getEnderecoSMTP(), 
/* 1213 */       this.persistencia.getPortaSMTP(), this.persistencia.isFlagAutentic(), this.persistencia.getUsuario(), this.persistencia.getSenha(), (String)this.persistencia.getIdioma());
/* 1214 */     email.setDaemon(true);
/*      */     
/* 1216 */     if (isGui()) {
/* 1217 */       email.setGui(this.gui);
/*      */     }
/* 1219 */     email.setMensagemErro("Erro_ao_enviar_o_Email");
/* 1220 */     email.start();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configuraEmail(String[] destinatarios, String remetente, String enderecoSMTP, int portaSMTP, boolean autenticacao, String usuario, String senha)
/*      */   {
/* 1238 */     this.destinatarios = destinatarios;
/* 1239 */     this.remetente = remetente;
/* 1240 */     this.enderecoSMTP = enderecoSMTP;
/* 1241 */     this.portaSMTP = portaSMTP;
/* 1242 */     this.autenticacao = autenticacao;
/* 1243 */     this.usuario = usuario;
/* 1244 */     this.senha = senha;
/* 1245 */     this.persistencia.setDestinatarios(destinatarios);
/* 1246 */     this.persistencia.setRemetente(remetente);
/* 1247 */     this.persistencia.setEnderecoSMTP(enderecoSMTP);
/* 1248 */     this.persistencia.setPortaSMTP(portaSMTP);
/* 1249 */     this.persistencia.setFlagAutentic(autenticacao);
/* 1250 */     this.persistencia.setUsuario(usuario);
/* 1251 */     this.persistencia.setSenha(senha);
/* 1252 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/* 1266 */     this.programacao = dias;
/*      */     
/* 1268 */     this.horaLigar = horaLigar;
/* 1269 */     this.minutoLigar = minutoLigar;
/* 1270 */     this.horaDesligar = horaDesligar;
/* 1271 */     this.minutoDesligar = minutoDesligar;
/*      */     
/* 1273 */     if (minutoDesligar >= 5) {
/* 1274 */       this.minutoDesligarAgendado = (minutoDesligar - 5);
/* 1275 */       this.horaDesligarAgendada = horaDesligar;
/*      */     } else {
/* 1277 */       this.minutoDesligarAgendado = (minutoDesligar - 5 + 60);
/* 1278 */       this.horaDesligarAgendada = (horaDesligar - 1);
/*      */     }
/* 1280 */     return programa();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean programa()
/*      */   {
/* 1303 */     GregorianCalendar horaAtual = new GregorianCalendar();
/*      */     
/* 1305 */     return this.ups.programa(this.programacao, this.horaLigar, this.minutoLigar, this.horaDesligar, this.minutoDesligar, 
/* 1306 */       horaAtual.get(7) - 1, horaAtual.get(5), 
/* 1307 */       horaAtual.get(2) + 1, horaAtual.get(1), 
/* 1308 */       horaAtual.get(13), horaAtual.get(12), 
/* 1309 */       horaAtual.get(11));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void desligaFimAutonomia()
/*      */   {
/* 1318 */     if (this.flagShutFimAut)
/*      */     {
/* 1320 */       if ((this.ups.getAutonomiaBateria() <= this.autonomiaMinima) && (!this.ups.isModoRede()))
/*      */       {
/* 1322 */         desligaUPS();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void salvaErro(String str)
/*      */   {
/*      */     try
/*      */     {
/* 1335 */       ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("erros.txt")));
/* 1336 */       output.writeObject(str);
/* 1337 */       output.flush();
/* 1338 */       output.close();
/*      */     } catch (Exception e) {
/* 1340 */       System.err.println(e.getMessage());
/* 1341 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaEventos(Evento[] evts)
/*      */   {
/* 1351 */     SalvaEventos salvaEventos = new SalvaEventos(evts, this.historicoEventos);
/* 1352 */     ExecutorService threadExecutor = Executors.newFixedThreadPool(1);
/* 1353 */     threadExecutor.execute(salvaEventos);
/*      */     
/* 1355 */     threadExecutor.shutdown();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean excluirEvento(int index)
/*      */   {
/* 1364 */     Evento[] temp = getArrayEventos();
/*      */     
/* 1366 */     if (this.historicoEventos.removerEvento(temp[index], temp[index].getMes(), temp[index].getAno())) {
/* 1367 */       removeEvento(temp[index]);
/*      */       
/* 1369 */       return true;
/*      */     }
/* 1371 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean excluirTodosEventos()
/*      */   {
/* 1394 */     this.historicoEventos.criaArquivo();
/* 1395 */     removeAllEvento();
/* 1396 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaConsumo()
/*      */   {
/* 1405 */     this.contConsumo += 1;
/* 1406 */     this.consumo += getPotenciaReal() / 3600000.0F;
/* 1407 */     if (this.contConsumo >= 3600)
/*      */     {
/* 1409 */       if (isGui()) {
/* 1410 */         SalvaConsumo salvaConsumo = new SalvaConsumo(this.consumo, this.gui.getPainelGraficos());
/* 1411 */         ExecutorService threadExecutor = Executors.newFixedThreadPool(1);
/* 1412 */         threadExecutor.execute(salvaConsumo);
/*      */       }
/*      */       
/*      */ 
/* 1416 */       this.consumo = 0.0F;
/* 1417 */       this.contConsumo = 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void putEventos(Evento e)
/*      */   {
/* 1424 */     if (e != null) {
/* 1425 */       this.vetorEventos.add(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void putEventos(Evento[] e)
/*      */   {
/* 1432 */     if (e != null) {
/* 1433 */       for (int i = 0; i < e.length; i++) {
/* 1434 */         if ((e[i] instanceof Evento)) {
/* 1435 */           this.vetorEventos.add(e[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean removeEvento(Evento evento)
/*      */   {
/* 1443 */     return this.vetorEventos.remove(evento);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean removeAllEvento()
/*      */   {
/* 1450 */     this.vetorEventos.clear();
/* 1451 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public Evento[] getArrayEventos()
/*      */   {
/* 1457 */     Evento[] temp = new Evento[this.vetorEventos.size()];
/* 1458 */     for (int i = 0; i < this.vetorEventos.size(); i++) {
/* 1459 */       if ((this.vetorEventos.get(i) instanceof Evento)) {
/* 1460 */         temp[i] = ((Evento)this.vetorEventos.get(i));
/*      */       }
/*      */     }
/*      */     
/* 1464 */     return temp;
/*      */   }
/*      */   
/*      */ 
/*      */   private Evento[] importArrayEvento()
/*      */   {
/* 1470 */     return this.historicoEventos.getEventos();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void notificaControle()
/*      */   {
/* 1477 */     notificaOuvintesControle(1);
/*      */     
/* 1479 */     this.programacao = this.ups.getDiasSemanaProgramados();
/*      */     
/*      */ 
/*      */ 
/* 1483 */     this.horaDesligar = this.ups.getHoraDesligar();
/* 1484 */     this.minutoDesligar = this.ups.getMinutoDesligar();
/* 1485 */     this.horaLigar = this.ups.getHoraLigar();
/* 1486 */     this.minutoLigar = this.ups.getMinutoLigar();
/*      */     
/* 1488 */     if (this.minutoDesligar >= 5) {
/* 1489 */       this.minutoDesligarAgendado = (this.minutoDesligar - 5);
/* 1490 */       this.horaDesligarAgendada = this.horaDesligar;
/*      */     } else {
/* 1492 */       this.minutoDesligarAgendado = (this.minutoDesligar - 5 + 60);
/* 1493 */       this.horaDesligarAgendada = (this.horaDesligar - 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1498 */     this.timerCom.restart();
/*      */     
/* 1500 */     if (this.falhaCom) {
/* 1501 */       this.falhaCom = false;
/*      */       
/*      */ 
/* 1504 */       atualizaCalendarioRelogio();
/* 1505 */       notificaOuvintesControle(3);
/* 1506 */       if ((this.persistencia != null) && 
/* 1507 */         (this.persistencia.getMensagens()[5][0] != 0)) {
/* 1508 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("Retorno_da_comunicacao_com_UPS__Horario"));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1513 */     if ((!this.ups.isModoRede()) && (!this.redeFalha))
/*      */     {
/* 1515 */       this.redeFalha = true;
/* 1516 */       if (this.shutdownTempoFalha) {
/* 1517 */         if (!this.timerFalha.isRunning()) {
/* 1518 */           this.timerFalha.start();
/*      */         } else
/* 1520 */           this.timerFalha.restart();
/*      */       }
/* 1522 */       notificaOuvintesControle(4);
/* 1523 */       if ((this.persistencia != null) && 
/* 1524 */         (this.persistencia.getMensagens()[0][0] != 0)) {
/* 1525 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("Falta_de_energia"));
/*      */       }
/*      */       
/*      */     }
/* 1529 */     else if ((this.ups.isModoRede()) && (this.redeFalha)) {
/* 1530 */       this.redeFalha = false;
/* 1531 */       notificaOuvintesControle(5);
/* 1532 */       if ((this.persistencia != null) && 
/* 1533 */         (this.persistencia.getMensagens()[0][0] != 0)) {
/* 1534 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("Retorno_de_energia"));
/*      */       }
/*      */     }
/*      */     
/* 1538 */     if ((this.ups.isBateriaBaixa()) && (!this.bateriaBaixa)) {
/* 1539 */       this.bateriaBaixa = true;
/* 1540 */       notificaOuvintesControle(6);
/* 1541 */       if ((this.persistencia != null) && 
/* 1542 */         (this.persistencia.getMensagens()[1][0] != 0)) {
/* 1543 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_bateria_esta_fraca"));
/*      */       }
/* 1545 */       this.bateriaBaixaScript = false;
/*      */     }
/* 1547 */     else if ((!this.ups.isBateriaBaixa()) && (this.bateriaBaixa)) {
/* 1548 */       this.bateriaBaixa = false;
/* 1549 */       notificaOuvintesControle(7);
/* 1550 */       if ((this.persistencia != null) && 
/* 1551 */         (this.persistencia.getMensagens()[1][0] != 0)) {
/* 1552 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_bateria_esta_normal"));
/*      */       }
/*      */     }
/*      */     
/* 1556 */     if ((this.ups.isCargaElevada()) && (!this.cargaElevada)) {
/* 1557 */       this.cargaElevada = true;
/* 1558 */       notificaOuvintesControle(8);
/* 1559 */       if ((this.persistencia != null) && 
/* 1560 */         (this.persistencia.getMensagens()[2][0] != 0)) {
/* 1561 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_carga_esta_elevada"));
/*      */       }
/*      */     }
/* 1564 */     else if ((!this.ups.isCargaElevada()) && (this.cargaElevada)) {
/* 1565 */       this.cargaElevada = false;
/* 1566 */       notificaOuvintesControle(9);
/* 1567 */       if ((this.persistencia != null) && 
/* 1568 */         (this.persistencia.getMensagens()[2][0] != 0)) {
/* 1569 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_carga_esta_normal"));
/*      */       }
/*      */     }
/*      */     
/* 1573 */     if ((this.ups.isTemperaturaElevada()) && (!this.temperaturaElevada)) {
/* 1574 */       this.temperaturaElevada = true;
/* 1575 */       notificaOuvintesControle(10);
/* 1576 */       if ((this.persistencia != null) && 
/* 1577 */         (this.persistencia.getMensagens()[3][0] != 0)) {
/* 1578 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_temperatura_esta_elevada"));
/*      */       }
/*      */     }
/* 1581 */     else if ((!this.ups.isTemperaturaElevada()) && (this.temperaturaElevada)) {
/* 1582 */       this.temperaturaElevada = false;
/* 1583 */       notificaOuvintesControle(11);
/* 1584 */       if ((this.persistencia != null) && 
/* 1585 */         (this.persistencia.getMensagens()[3][0] != 0)) {
/* 1586 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_temperatura_esta_normal"));
/*      */       }
/*      */     }
/*      */     
/* 1590 */     if ((this.ups.isUsandoSomenteBateria()) && (!this.usandoSomenteBateria)) {
/* 1591 */       this.usandoSomenteBateria = true;
/* 1592 */       notificaOuvintesControle(12);
/*      */       
/* 1594 */       if ((this.persistencia != null) && 
/* 1595 */         (this.persistencia.getMensagens()[4][0] != 0)) {
/* 1596 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_bateria_esta_em_uso"));
/*      */       }
/*      */     }
/* 1599 */     else if ((!this.ups.isUsandoSomenteBateria()) && (this.usandoSomenteBateria)) {
/* 1600 */       this.usandoSomenteBateria = false;
/* 1601 */       notificaOuvintesControle(13);
/* 1602 */       if ((this.persistencia != null) && 
/* 1603 */         (this.persistencia.getMensagens()[4][0] != 0)) {
/* 1604 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("A_bateria_nao_esta_mais_em_uso"));
/*      */       }
/*      */     }
/*      */     
/* 1608 */     this.eventos = this.ups.getEventos();
/* 1609 */     if (this.eventos != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1623 */       salvaEventos(this.eventos);
/* 1624 */       putEventos(this.eventos);
/* 1625 */       verificaArquivoLote(this.eventos);
/*      */     }
/*      */     
/* 1628 */     salvaConsumo();
/*      */     
/*      */ 
/*      */ 
/* 1632 */     desligaFimAutonomia();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addEventos(Evento[] eventos)
/*      */   {
/* 1644 */     salvaEventos(eventos);
/* 1645 */     putEventos(eventos);
/* 1646 */     verificaArquivoLote(eventos);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void actionPerformed(ActionEvent ae)
/*      */   {
/* 1656 */     if (ae.getSource() == this.timerShut)
/*      */     {
/* 1658 */       if ((this.programacao[this.ups.getDiaSemana()] != 0) && ((this.ups.getHora() == this.horaDesligarAgendada) || (this.ups.getHora() == this.horaDesligar)) && 
/* 1659 */         (this.ups.getMinutos() >= this.minutoDesligarAgendado) && (this.ups.getMinutos() <= this.minutoDesligar)) {
/* 1660 */         desligaUPS();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1665 */     if (ae.getSource() == this.timerCom)
/*      */     {
/*      */ 
/* 1668 */       notificaOuvintesControle(2);
/* 1669 */       this.protocolo.setModoContinuo();
/* 1670 */       if ((this.persistencia != null) && 
/* 1671 */         (this.persistencia.getMensagens()[5][0] != 0))
/*      */       {
/* 1673 */         enviaEmail(ResourceBundle.getBundle(Idioma.getIdioma()).getString("Sem_comunicacao_com_o_UPS__Horario") + 
/* 1674 */           this.formatador.format(getHora()) + ":" + this.formatador.format(getMinutos()));
/*      */       }
/* 1676 */       this.falhaCom = true;
/* 1677 */       this.driver.restart();
/*      */       
/* 1679 */       this.timerCom.stop();
/*      */     }
/*      */     
/*      */ 
/* 1683 */     if (ae.getSource() == this.timerFalha)
/*      */     {
/* 1685 */       if ((!this.ups.isModoRede()) && (this.redeFalha))
/*      */       {
/* 1687 */         if (this.shutdownTempoFalha)
/*      */         {
/* 1689 */           desligaUPS();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void fechaPersistenciaServico()
/*      */   {
/* 1697 */     this.persistencia.salvaConf();
/* 1698 */     this.persistencia.setSgmConf(null);
/* 1699 */     this.persistencia = null;
/*      */   }
/*      */   
/*      */   public void abrePersistenciaServico() {
/* 1703 */     this.persistencia = new Persistencia(PathConfig.getPathXML());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void encerraAplicativo(Object idioma, int evento, String script, boolean[][] mensagens, int expBat, String nomeUPS, String smtp, boolean flagAuten, String usuario, String senha, String remetente, String[] destinatario, int portaSMTP, int portaPedidos, String comunaLeitura, String comunaEscrita, String enderecoGerente, int portaEnvio, int vSnmp, int protocolo, int autonomia, int falhaEletrica, int portaRemota, boolean modo, double valorKW, boolean shutFim, boolean falha, String provedorEscolhido)
/*      */   {
/* 1718 */     if (this.persistencia.getFamilia() != 0)
/*      */     {
/* 1720 */       if (isGui()) {
/* 1721 */         SalvaConsumo salvaConsumo = new SalvaConsumo(this.consumo, this.gui.getPainelGraficos());
/* 1722 */         ExecutorService threadExecutor = Executors.newFixedThreadPool(1);
/* 1723 */         threadExecutor.execute(salvaConsumo);
/* 1724 */         threadExecutor.shutdown();
/*      */       }
/*      */       
/* 1727 */       this.persistencia.setFamilia(this.familiaUPS);
/* 1728 */       this.persistencia.setModeloUPS(this.modeloUPS);
/* 1729 */       this.persistencia.setPorta(this.driver.getNomePortaAtual());
/* 1730 */       this.persistencia.setIdioma(idioma);
/* 1731 */       this.persistencia.setEvento(evento);
/* 1732 */       this.persistencia.setScript(script);
/* 1733 */       this.persistencia.setMensagens(mensagens);
/* 1734 */       this.persistencia.setExpansorBateria(expBat);
/* 1735 */       this.persistencia.setNomeUPS(nomeUPS);
/* 1736 */       this.persistencia.setEnderecoSMTP(smtp);
/* 1737 */       this.persistencia.setFlagAutentic(flagAuten);
/* 1738 */       this.persistencia.setUsuario(usuario);
/* 1739 */       this.persistencia.setSenha(senha);
/* 1740 */       this.persistencia.setRemetente(remetente);
/* 1741 */       this.persistencia.setDestinatarios(destinatario);
/* 1742 */       this.persistencia.setPortaSMTP(portaSMTP);
/* 1743 */       this.persistencia.setPortaPedidos(portaPedidos);
/* 1744 */       this.persistencia.setComunidadeLeitura(comunaLeitura);
/* 1745 */       this.persistencia.setComunidadeEscrita(comunaEscrita);
/* 1746 */       this.persistencia.setEnderecoGerente(enderecoGerente);
/* 1747 */       this.persistencia.setPortaEnvio(portaEnvio);
/* 1748 */       this.persistencia.setVSNMP(vSnmp);
/* 1749 */       this.persistencia.setProtocoloTransporte(protocolo);
/* 1750 */       this.persistencia.setAutonoMinima(autonomia);
/* 1751 */       this.persistencia.setFlagShutFimAut(shutFim);
/* 1752 */       this.persistencia.setFlagShutFalhaEletrica(falha);
/* 1753 */       this.persistencia.setTempoFalhaEletrica(falhaEletrica);
/* 1754 */       this.persistencia.setValorKw(valorKW);
/* 1755 */       this.persistencia.setProvedorEscolhido(provedorEscolhido);
/*      */       
/* 1757 */       salvaPortaRemota(portaRemota);
/* 1758 */       salvaModoRemoto(modo);
/* 1759 */       this.persistencia.salvaConf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1768 */     if (isGui())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1774 */       if (this.sistemaOperacional.indexOf("windows") >= 0) {
/* 1775 */         new ServicoWin().iniciar();
/*      */       } else {
/* 1777 */         new ClienteRMI().abrirPortaUsbDoServico(this);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1782 */     System.exit(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPersistenciaSNMP(int portaPedidos, String comunaLeitura, String comunaEscrita, String enderecoGerente, int portaEnvio, int vSnmp, int protocolo)
/*      */   {
/* 1802 */     this.persistencia.setPortaPedidos(portaPedidos);
/* 1803 */     this.persistencia.setComunidadeLeitura(comunaLeitura);
/* 1804 */     this.persistencia.setComunidadeEscrita(comunaEscrita);
/* 1805 */     this.persistencia.setEnderecoGerente(enderecoGerente);
/* 1806 */     this.persistencia.setPortaEnvio(portaEnvio);
/* 1807 */     this.persistencia.setVSNMP(vSnmp);
/* 1808 */     this.persistencia.setProtocoloTransporte(protocolo);
/* 1809 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaMensagens(boolean[][] mensagens)
/*      */   {
/* 1818 */     this.persistencia.setMensagens(mensagens);
/* 1819 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPortaSMTP(int porta)
/*      */   {
/* 1827 */     this.persistencia.setPortaSMTP(porta);
/* 1828 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaModoLogging(boolean b)
/*      */   {
/* 1837 */     this.valuesLog.setLogEnabled(b);
/* 1838 */     this.persistencia.setModoLogging(b);
/* 1839 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPortaPedidos(int portaPedidos)
/*      */   {
/* 1847 */     this.persistencia.setPortaPedidos(portaPedidos);
/* 1848 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaComunidadeLeitura(String comunaLeitura)
/*      */   {
/* 1856 */     this.persistencia.setComunidadeLeitura(comunaLeitura);
/* 1857 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaComunidadeEscrita(String comunaEscrita)
/*      */   {
/* 1865 */     this.persistencia.setComunidadeEscrita(comunaEscrita);
/* 1866 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaEnderecoGerente(String enderecoGerente)
/*      */   {
/* 1874 */     this.persistencia.setEnderecoGerente(enderecoGerente);
/* 1875 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPortaEnvio(int portaEnvio)
/*      */   {
/* 1883 */     this.persistencia.setPortaEnvio(portaEnvio);
/* 1884 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaVSnmp(int vSnmp)
/*      */   {
/* 1892 */     this.persistencia.setVSNMP(vSnmp);
/* 1893 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaProtocolo(int protocolo)
/*      */   {
/* 1901 */     this.persistencia.setProtocoloTransporte(protocolo);
/* 1902 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaExpansorBateria(int expansor)
/*      */   {
/* 1910 */     this.persistencia.setExpansorBateria(expansor);
/* 1911 */     this.persistencia.salvaConf();
/* 1912 */     this.expansorBateria = expansor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaNomeUPS(String nomeUPS)
/*      */   {
/* 1920 */     this.persistencia.setNomeUPS(nomeUPS);
/* 1921 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaEventoGatilho(int evento)
/*      */   {
/* 1929 */     this.persistencia.setEvento(evento);
/* 1930 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPortaRemota(int porta)
/*      */   {
/* 1938 */     this.persistencia.setPortaRemota(porta);
/* 1939 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaModoRemoto(boolean modo)
/*      */   {
/* 1947 */     this.persistencia.setModoRemoto(modo);
/* 1948 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaScript(String script)
/*      */   {
/* 1956 */     this.persistencia.setScript(script);
/* 1957 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaVSnmp2(Integer vSnmp)
/*      */   {
/* 1965 */     this.persistencia.setVSNMP(vSnmp.intValue() - 1);
/* 1966 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaValorKw(double valor)
/*      */   {
/* 1974 */     this.persistencia.setValorKw(valor);
/* 1975 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaProtocolo(String protocolo)
/*      */   {
/* 1983 */     if (protocolo.equalsIgnoreCase("UDP")) {
/* 1984 */       this.persistencia.setProtocoloTransporte(0);
/*      */     } else
/* 1986 */       this.persistencia.setProtocoloTransporte(1);
/* 1987 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configuraSGM()
/*      */   {
/* 1995 */     this.familiaUPS = this.persistencia.getFamilia();
/* 1996 */     this.modeloUPS = this.persistencia.getModeloUPS();
/* 1997 */     configuraUPSEtapa1(this.familiaUPS, this.persistencia.getPorta());
/* 1998 */     this.protocolo.addProtocoloListener(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configuraAutoTeste(boolean enable, int hora, int minuto, int periodo)
/*      */   {
/* 2009 */     this.autoTesteHandler.setAutoTesteHabilitado(enable);
/* 2010 */     this.autoTesteHandler.setHoraTeste(hora);
/* 2011 */     this.autoTesteHandler.setMinutoTeste(minuto);
/* 2012 */     this.autoTesteHandler.setPeriodoAutoTeste(periodo);
/*      */     
/* 2014 */     this.persistencia.setAutoTeste(enable);
/* 2015 */     this.persistencia.setHoraTeste(hora);
/* 2016 */     this.persistencia.setMinutoTeste(minuto);
/* 2017 */     this.persistencia.setPeriodoAutoTeste(periodo);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean desligaEntrada()
/*      */   {
/* 2024 */     return this.ups.desligaEntrada();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ligaEntrada()
/*      */   {
/* 2030 */     return this.ups.ligaEntrada();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desligaSaida()
/*      */   {
/* 2036 */     return this.ups.desligaSaida();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ligaSaida()
/*      */   {
/* 2042 */     return this.ups.ligaSaida();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ativaBypass()
/*      */   {
/* 2048 */     return this.ups.ativaBypass();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desativaBypass()
/*      */   {
/* 2054 */     return this.ups.desativaBypass();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean iniciarAutoteste()
/*      */   {
/* 2060 */     return this.ups.iniciarAutoteste();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean finalizarAutoteste()
/*      */   {
/* 2066 */     return this.ups.finalizarAutoteste();
/*      */   }
/*      */   
/*      */   public void downloadEventos()
/*      */   {
/* 2071 */     this.ups.downloadEventos();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isTesteExecutando()
/*      */   {
/* 2077 */     return this.ups.isTesteExecutando();
/*      */   }
/*      */   
/*      */ 
/*      */   public float getFrequenciaBypass()
/*      */   {
/* 2083 */     return this.ups.getFrequenciaBypass();
/*      */   }
/*      */   
/*      */ 
/*      */   public float getTensaoBypass()
/*      */   {
/* 2089 */     return this.ups.getTensaoBypass();
/*      */   }
/*      */   
/*      */ 
/*      */   public float getCorrenteBypass()
/*      */   {
/* 2095 */     return this.ups.getCorrenteBypass();
/*      */   }
/*      */   
/*      */ 
/*      */   public float getPotenciaBypass()
/*      */   {
/* 2101 */     return this.ups.getPotenciaBypass();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/* 2109 */     Controle sgmLite = new Controle();
/*      */     
/*      */ 
/* 2112 */     if (sgmLite.persistencia.isPrimeiraExec()) {
/* 2113 */       sgmLite.primeiraExecucao = true;
/*      */     } else {
/* 2115 */       sgmLite.primeiraExecucao = false;
/* 2116 */       sgmLite.configuraSGM();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean setFamiliaUPS(int familiaUPS)
/*      */   {
/* 2130 */     if (this.driver.getNomePortaAtual() != null)
/*      */     {
/*      */ 
/* 2133 */       if (!this.driver.close()) {
/* 2134 */         return false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2139 */     switch (familiaUPS) {
/*      */     case 1: 
/* 2141 */       this.protocolo = new ProtocoloSolis();
/* 2142 */       break;
/*      */     case 2: 
/* 2144 */       this.protocolo = new ProtocoloRhino();
/* 2145 */       break;
/*      */     case 3: 
/* 2147 */       this.protocolo = new ProtocoloPS();
/* 2148 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/* 2155 */       return false;
/*      */     }
/*      */     
/* 2158 */     this.familiaUPS = familiaUPS;
/* 2159 */     this.driver.addDriverListener(this.protocolo);
/* 2160 */     this.protocolo.setComunicador(this.driver);
/*      */     
/* 2162 */     this.persistencia.setFamilia(familiaUPS);
/*      */     
/*      */ 
/* 2165 */     this.protocolo.addProtocoloListener(this);
/*      */     
/*      */ 
/* 2168 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIdioma(String idioma)
/*      */   {
/* 2176 */     this.idioma = idioma;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean setPorta(String porta)
/*      */   {
/* 2192 */     this.parametrosXML.setNomeDocumento(PathConfig.getPathXML() + "comunicacao.xml");
/*      */     
/* 2194 */     String[] parametros = new String[7];
/*      */     
/*      */     String familia;
/*      */     
/*      */     String familia;
/*      */     String familia;
/* 2200 */     switch (this.familiaUPS) {
/*      */     case 1: 
/* 2202 */       familia = "solis";
/* 2203 */       break;
/*      */     case 2: 
/* 2205 */       familia = "rhino";
/* 2206 */       break;
/*      */     case 3: 
/* 2208 */       familia = "stay";
/* 2209 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/* 2216 */       return false;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */     String familia;
/*      */     
/*      */ 
/* 2224 */     String[] temp = this.parametrosXML.configuraPorta(familia, "serial");
/*      */     
/*      */ 
/* 2227 */     parametros[0] = porta;
/*      */     
/* 2229 */     parametros[1] = temp[0];
/* 2230 */     parametros[2] = temp[1];
/* 2231 */     parametros[3] = temp[2];
/* 2232 */     parametros[4] = temp[3];
/* 2233 */     parametros[5] = temp[4];
/* 2234 */     parametros[6] = temp[5];
/*      */     
/* 2236 */     if (this.driver.getNomePortaAtual() != null) {
/* 2237 */       this.driver.close();
/*      */     }
/* 2239 */     boolean flagOpen = this.driver.open(parametros);
/* 2240 */     if (flagOpen) {
/* 2241 */       if (this.familiaUPS == 2)
/* 2242 */         this.protocolo.pedidoDados();
/* 2243 */       return true;
/*      */     }
/*      */     
/* 2246 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDelayTimerCom(int delayTimerCom)
/*      */   {
/* 2257 */     this.delayTimerCom = delayTimerCom;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEventoGatilho(int eventoGatilho)
/*      */   {
/* 2267 */     this.eventoGatilho = eventoGatilho;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCaminhoScript(String caminhoScript)
/*      */   {
/* 2277 */     this.caminhoScript = caminhoScript;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNomePCUPS(String nomePCUPS)
/*      */   {
/* 2287 */     this.nomePCUPS = nomePCUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTempoFalha(int tempoFalha)
/*      */   {
/* 2296 */     this.tempoFalha = (tempoFalha * 60 * 1000 / 2);
/* 2297 */     this.persistencia.setTempoFalhaEletrica(tempoFalha);
/* 2298 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNomeUPS(String nomeUPS)
/*      */   {
/* 2308 */     this.nomeUPS = nomeUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutenticacao(boolean autenticacao)
/*      */   {
/* 2318 */     this.autenticacao = autenticacao;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDestinatarios(String[] destinatarios)
/*      */   {
/* 2328 */     this.destinatarios = destinatarios;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEnderecoSMTP(String enderecoSMTP)
/*      */   {
/* 2338 */     this.enderecoSMTP = enderecoSMTP;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPortaSMTP(int portaSMTP)
/*      */   {
/* 2348 */     this.portaSMTP = portaSMTP;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRemetente(String remetente)
/*      */   {
/* 2358 */     this.remetente = remetente;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSenha(String senha)
/*      */   {
/* 2368 */     this.senha = senha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUsuario(String usuario)
/*      */   {
/* 2378 */     this.usuario = usuario;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMensagens(boolean[][] mensagens)
/*      */   {
/* 2395 */     this.mensagens = mensagens;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExpansorBateria(int expansorBateria)
/*      */   {
/* 2406 */     if ((expansorBateria > 0) && (this.ups != null)) {
/* 2407 */       this.ups.setExpansorBateria(expansorBateria);
/*      */     }
/* 2409 */     this.expansorBateria = expansorBateria;
/*      */   }
/*      */   
/*      */   public void setAgenteSNMP(AgenteSGM agenteSNMP)
/*      */   {
/* 2414 */     this.agenteSNMP = agenteSNMP;
/*      */   }
/*      */   
/*      */   public void setBateriaBaixa(boolean bateriaBaixa) {
/* 2418 */     this.bateriaBaixa = bateriaBaixa;
/*      */   }
/*      */   
/*      */   public void setCargaElevada(boolean cargaElevada) {
/* 2422 */     this.cargaElevada = cargaElevada;
/*      */   }
/*      */   
/*      */   public void setDriver(Communication driver) {
/* 2426 */     this.driver = driver;
/*      */   }
/*      */   
/*      */   public void setEnderecoIPLocal(String enderecoIPLocal) {
/* 2430 */     this.enderecoIPLocal = enderecoIPLocal;
/*      */   }
/*      */   
/*      */   public void setEventos(Evento[] eventos) {
/* 2434 */     this.eventos = eventos;
/*      */   }
/*      */   
/*      */   public void setFalhaCom(boolean falhaCom) {
/* 2438 */     this.falhaCom = falhaCom;
/*      */   }
/*      */   
/*      */   public void setFormatador(DecimalFormat formatador) {
/* 2442 */     this.formatador = formatador;
/*      */   }
/*      */   
/*      */   public void setGui(InterfaceGrafica gui) {
/* 2446 */     this.gui = gui;
/*      */   }
/*      */   
/*      */   public void setHistCos(HistoricoConsumo histCos) {
/* 2450 */     this.histCos = histCos;
/*      */   }
/*      */   
/*      */   public void setHoraDesligarAgendada(int horaDesligarAgendada) {
/* 2454 */     this.horaDesligarAgendada = horaDesligarAgendada;
/*      */   }
/*      */   
/*      */   public void setMinutoDesligarAgendado(int minutoDesligarAgendado) {
/* 2458 */     this.minutoDesligarAgendado = minutoDesligarAgendado;
/*      */   }
/*      */   
/*      */   public void setModeloUPS(int modeloUPS) {
/* 2462 */     this.modeloUPS = modeloUPS;
/*      */   }
/*      */   
/*      */   public void setOuvintesControle(ControleListener[] ouvintesControle) {
/* 2466 */     this.ouvintesControle = ouvintesControle;
/*      */   }
/*      */   
/*      */   public void setParametrosXML(BuscaParametrosXML parametrosXML) {
/* 2470 */     this.parametrosXML = parametrosXML;
/*      */   }
/*      */   
/*      */   public void setPersistencia(Persistencia persistencia) {
/* 2474 */     this.persistencia = persistencia;
/*      */   }
/*      */   
/*      */   public void setPrimeiraExecucao(boolean primeiraExecucao) {
/* 2478 */     this.primeiraExecucao = primeiraExecucao;
/*      */   }
/*      */   
/*      */   public void setProgramacao(boolean[] programacao) {
/* 2482 */     this.programacao = programacao;
/*      */   }
/*      */   
/*      */   public void setProtocolo(ProtocoloUPS protocolo) {
/* 2486 */     this.protocolo = protocolo;
/*      */   }
/*      */   
/*      */   public void setRedeFalha(boolean redeFalha) {
/* 2490 */     this.redeFalha = redeFalha;
/*      */   }
/*      */   
/*      */   public void setSistemaOperacional(String sistemaOperacional) {
/* 2494 */     this.sistemaOperacional = sistemaOperacional;
/*      */   }
/*      */   
/*      */   public void setTemperaturaElevada(boolean temperaturaElevada) {
/* 2498 */     this.temperaturaElevada = temperaturaElevada;
/*      */   }
/*      */   
/*      */   public void setTimerCom(Timer timerCom) {
/* 2502 */     this.timerCom = timerCom;
/*      */   }
/*      */   
/*      */   public void setTimerFalha(Timer timerFalha) {
/* 2506 */     this.timerFalha = timerFalha;
/*      */   }
/*      */   
/*      */   public void setTimerShut(Timer timerShut) {
/* 2510 */     this.timerShut = timerShut;
/*      */   }
/*      */   
/*      */   public void setUps(AbstractUPS ups) {
/* 2514 */     this.ups = ups;
/*      */   }
/*      */   
/*      */   public void setUsandoSomenteBateria(boolean usandoSomenteBateria) {
/* 2518 */     this.usandoSomenteBateria = usandoSomenteBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFlagShutFimAut(boolean flagShutFimAut)
/*      */   {
/* 2529 */     this.flagShutFimAut = flagShutFimAut;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShutdownTempoFalha(boolean shutdownTempoFalha)
/*      */   {
/* 2540 */     this.shutdownTempoFalha = shutdownTempoFalha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutonomiaMinima(int autonomiaMinima)
/*      */   {
/* 2550 */     this.autonomiaMinima = autonomiaMinima;
/* 2551 */     this.persistencia.setAutonoMinima(autonomiaMinima);
/* 2552 */     this.persistencia.salvaConf();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNomePCUPS()
/*      */   {
/* 2560 */     return this.nomePCUPS;
/*      */   }
/*      */   
/*      */   public String getNomeUPS() {
/* 2564 */     return this.nomeUPS;
/*      */   }
/*      */   
/*      */   public int getAno() {
/* 2568 */     return this.ups.getAno();
/*      */   }
/*      */   
/*      */   public int getAutonomiaBateria() {
/* 2572 */     return this.ups.getAutonomiaBateria();
/*      */   }
/*      */   
/*      */   public boolean isBateriaBaixa() {
/* 2576 */     return this.ups.isBateriaBaixa();
/*      */   }
/*      */   
/*      */   public boolean isModoRemoto() {
/* 2580 */     return this.persistencia.isModoRemoto();
/*      */   }
/*      */   
/*      */   public boolean isBateriaCarregada() {
/* 2584 */     return this.ups.isBateriaCarregada();
/*      */   }
/*      */   
/*      */   public boolean isBateriaCritica() {
/* 2588 */     return this.ups.isBateriaCritica();
/*      */   }
/*      */   
/*      */   public boolean isBateriaDescarregada() {
/* 2592 */     return this.ups.isBateriaDescarregada();
/*      */   }
/*      */   
/*      */   public boolean isBypassAtivado() {
/* 2596 */     return this.ups.isBypassAtivado();
/*      */   }
/*      */   
/*      */   public boolean isCargaElevada() {
/* 2600 */     return this.ups.isCargaElevada();
/*      */   }
/*      */   
/*      */   public boolean isCarregandoBateria() {
/* 2604 */     return this.ups.isCarregandoBateria();
/*      */   }
/*      */   
/*      */   public boolean isUsandoSomenteBateria()
/*      */   {
/* 2609 */     return this.ups.isUsandoSomenteBateria();
/*      */   }
/*      */   
/*      */   public float getCorrenteEntrada() {
/* 2613 */     return this.ups.getCorrenteEntrada();
/*      */   }
/*      */   
/*      */   public float getCorrenteSaida() {
/* 2617 */     return this.ups.getCorrenteSaida();
/*      */   }
/*      */   
/*      */   public int getDiaMes() {
/* 2621 */     return this.ups.getDiaMes();
/*      */   }
/*      */   
/*      */   public int getDiaSemana() {
/* 2625 */     return this.ups.getDiaSemana();
/*      */   }
/*      */   
/*      */   public boolean[] getDiasSemanaProgramados() {
/* 2629 */     return this.ups.getDiasSemanaProgramados();
/*      */   }
/*      */   
/*      */   public boolean isEntradaLigada() {
/* 2633 */     return this.ups.isEntradaLigada();
/*      */   }
/*      */   
/*      */   public int getExpansorBateria() {
/* 2637 */     return this.ups.getExpansorBateria();
/*      */   }
/*      */   
/*      */   public float getFatorPotenciaCarga() {
/* 2641 */     return this.ups.getFatorPotenciaCarga();
/*      */   }
/*      */   
/*      */   public float getFrequenciaEntrada() {
/* 2645 */     return this.ups.getFrequenciaEntrada();
/*      */   }
/*      */   
/*      */   public float getFrequenciaSaida() {
/* 2649 */     return this.ups.getFrequenciaSaida();
/*      */   }
/*      */   
/*      */   public int getHora() {
/* 2653 */     return new GregorianCalendar().get(11);
/*      */   }
/*      */   
/*      */   public int getHoraDesligar() {
/* 2657 */     return this.ups.getHoraDesligar();
/*      */   }
/*      */   
/*      */   public int getHoraLigar() {
/* 2661 */     return this.ups.getHoraLigar();
/*      */   }
/*      */   
/*      */   public float getLimiteInferiorTensaoEntrada() {
/* 2665 */     return this.ups.getLimiteInferiorTensaoEntrada();
/*      */   }
/*      */   
/*      */   public float getLimiteInferiorTensaoSaida() {
/* 2669 */     return this.ups.getLimiteInferiorTensaoSaida();
/*      */   }
/*      */   
/*      */   public float getLimiteSuperiorTensaoEntrada() {
/* 2673 */     return this.ups.getLimiteSuperiorTensaoEntrada();
/*      */   }
/*      */   
/*      */   public float getLimiteSuperiorTensaoSaida() {
/* 2677 */     return this.ups.getLimiteSuperiorTensaoSaida();
/*      */   }
/*      */   
/*      */   public int getMes() {
/* 2681 */     return this.ups.getMes();
/*      */   }
/*      */   
/*      */   public int getMinutoDesligar() {
/* 2685 */     return this.ups.getMinutoDesligar();
/*      */   }
/*      */   
/*      */   public int getMinutoLigar() {
/* 2689 */     return this.ups.getMinutoLigar();
/*      */   }
/*      */   
/*      */   public int getMinutos() {
/* 2693 */     return new GregorianCalendar().get(12);
/*      */   }
/*      */   
/*      */   public boolean isModoBateria() {
/* 2697 */     return this.ups.isModoBateria();
/*      */   }
/*      */   
/*      */   public boolean isFlagShutdown() {
/* 2701 */     return this.flagShutdown;
/*      */   }
/*      */   
/*      */   public boolean isModoBypass() {
/* 2705 */     return this.ups.isModoBypass();
/*      */   }
/*      */   
/*      */   public boolean isModoRede() {
/* 2709 */     return this.ups.isModoRede();
/*      */   }
/*      */   
/*      */   public int getPercentualBateria() {
/* 2713 */     return this.ups.getPercentualBateria();
/*      */   }
/*      */   
/*      */   public float getPotenciaAparente() {
/* 2717 */     return this.ups.getPotenciaAparente();
/*      */   }
/*      */   
/*      */   public float getPotenciaReal() {
/* 2721 */     return this.ups.getPotenciaReal();
/*      */   }
/*      */   
/*      */   public ProtocoloUPS getProtocoloUPS() {
/* 2725 */     return this.ups.getProtocoloUPS();
/*      */   }
/*      */   
/*      */   public boolean isSaidaLigada() {
/* 2729 */     return this.ups.isSaidaLigada();
/*      */   }
/*      */   
/*      */   public int getSegundos() {
/* 2733 */     return this.ups.getSegundos();
/*      */   }
/*      */   
/*      */   public boolean isSobrecarga() {
/* 2737 */     return this.ups.isSobrecarga();
/*      */   }
/*      */   
/*      */   public boolean isSuperAquecimento() {
/* 2741 */     return this.ups.isSuperAquecimento();
/*      */   }
/*      */   
/*      */   public boolean isTemperaturaElevada() {
/* 2745 */     return this.ups.isTemperaturaElevada();
/*      */   }
/*      */   
/*      */   public float getTemperaturaUPS() {
/* 2749 */     return this.ups.getTemperaturaUPS();
/*      */   }
/*      */   
/*      */   public float getTensaoBateria() {
/* 2753 */     return this.ups.getTensaoBateria();
/*      */   }
/*      */   
/*      */   public float getTensaoBoost() {
/* 2757 */     return this.ups.getTensaoBoost();
/*      */   }
/*      */   
/*      */   public float getTensaoEntrada() {
/* 2761 */     return this.ups.getTensaoEntrada();
/*      */   }
/*      */   
/*      */   public float getTensaoEntradaNominal() {
/* 2765 */     return this.ups.getTensaoEntradaNominal();
/*      */   }
/*      */   
/*      */   public boolean isTensaoEntrada220() {
/* 2769 */     return this.ups.isTensaoEntrada220();
/*      */   }
/*      */   
/*      */   public float getTensaoSaida() {
/* 2773 */     return this.ups.getTensaoSaida();
/*      */   }
/*      */   
/*      */   public boolean isTensaoSaida220() {
/* 2777 */     return this.ups.isTensaoSaida220();
/*      */   }
/*      */   
/*      */   public float getTensaoSaidaNominal() {
/* 2781 */     return this.ups.getTensaoSaidaNominal();
/*      */   }
/*      */   
/*      */   public float getBateriaTensaoNominal() {
/* 2785 */     return this.ups.getBateriaTensaoNominal();
/*      */   }
/*      */   
/*      */   public int getPotenciaNominalVA() {
/* 2789 */     return this.ups.getPotenciaNominalVA();
/*      */   }
/*      */   
/*      */   public int getPotenciaNominalW() {
/* 2793 */     return this.ups.getPotenciaNominalW();
/*      */   }
/*      */   
/*      */   public float getTemperaturaCritica() {
/* 2797 */     return this.ups.getTemperaturaCritica();
/*      */   }
/*      */   
/*      */   public static int getRHINO() {
/* 2801 */     return 2;
/*      */   }
/*      */   
/*      */   public static int getSOLIS() {
/* 2805 */     return 1;
/*      */   }
/*      */   
/*      */   public static int getSOLIS_M11() {
/* 2809 */     return 171;
/*      */   }
/*      */   
/*      */   public AgenteSGM getAgenteSNMP() {
/* 2813 */     return this.agenteSNMP;
/*      */   }
/*      */   
/*      */   public boolean isAutenticacao() {
/* 2817 */     return this.autenticacao;
/*      */   }
/*      */   
/*      */   public int getAutonomiaMinima() {
/* 2821 */     return this.autonomiaMinima;
/*      */   }
/*      */   
/*      */   public int getAutonomiaMinimaPersistencia() {
/* 2825 */     return this.persistencia.getAutonoMinima();
/*      */   }
/*      */   
/*      */   public String getCaminhoScript() {
/* 2829 */     return this.caminhoScript;
/*      */   }
/*      */   
/*      */   public int getDelayTimerCom() {
/* 2833 */     return this.delayTimerCom;
/*      */   }
/*      */   
/*      */   public String[] getDestinatarios() {
/* 2837 */     return this.destinatarios;
/*      */   }
/*      */   
/*      */   public Communication getDriver() {
/* 2841 */     return this.driver;
/*      */   }
/*      */   
/*      */   public String getEnderecoIPLocal() {
/* 2845 */     return this.enderecoIPLocal;
/*      */   }
/*      */   
/*      */   public String getEnderecoSMTP() {
/* 2849 */     return this.enderecoSMTP;
/*      */   }
/*      */   
/*      */   public int getEventoGatilho() {
/* 2853 */     return this.eventoGatilho;
/*      */   }
/*      */   
/*      */   public boolean isFalhaCom() {
/* 2857 */     return this.falhaCom;
/*      */   }
/*      */   
/*      */   public int getFamiliaUPS() {
/* 2861 */     return this.familiaUPS;
/*      */   }
/*      */   
/*      */   public boolean isFlagShutFimAut() {
/* 2865 */     return this.flagShutFimAut;
/*      */   }
/*      */   
/*      */   public DecimalFormat getFormatador() {
/* 2869 */     return this.formatador;
/*      */   }
/*      */   
/*      */   public InterfaceGrafica getGui() {
/* 2873 */     return this.gui;
/*      */   }
/*      */   
/*      */   public HistoricoConsumo getHistCos() {
/* 2877 */     return this.histCos;
/*      */   }
/*      */   
/*      */   public int getHoraDesligarAgendada() {
/* 2881 */     return this.horaDesligarAgendada;
/*      */   }
/*      */   
/*      */   public String getIdioma() {
/* 2885 */     return this.idioma;
/*      */   }
/*      */   
/*      */   public boolean[][] getMensagens() {
/* 2889 */     return this.mensagens;
/*      */   }
/*      */   
/*      */   public int getMinutoDesligarAgendado() {
/* 2893 */     return this.minutoDesligarAgendado;
/*      */   }
/*      */   
/*      */   public int getModeloUPS() {
/* 2897 */     return this.modeloUPS;
/*      */   }
/*      */   
/*      */   public ControleListener[] getOuvintesControle() {
/* 2901 */     return this.ouvintesControle;
/*      */   }
/*      */   
/*      */   public BuscaParametrosXML getParametrosXML() {
/* 2905 */     return this.parametrosXML;
/*      */   }
/*      */   
/*      */   public Persistencia getPersistencia() {
/* 2909 */     return this.persistencia;
/*      */   }
/*      */   
/*      */   public String getPorta() {
/* 2913 */     return this.porta;
/*      */   }
/*      */   
/*      */   public int getPortaSMTP() {
/* 2917 */     return this.portaSMTP;
/*      */   }
/*      */   
/*      */   public boolean[] getProgramacao() {
/* 2921 */     return this.programacao;
/*      */   }
/*      */   
/*      */   public ProtocoloUPS getProtocolo() {
/* 2925 */     return this.protocolo;
/*      */   }
/*      */   
/*      */   public String getRemetente() {
/* 2929 */     return this.remetente;
/*      */   }
/*      */   
/*      */   public String getSenha() {
/* 2933 */     return this.senha;
/*      */   }
/*      */   
/*      */   public boolean isShutdownTempoFalha() {
/* 2937 */     return this.shutdownTempoFalha;
/*      */   }
/*      */   
/*      */   public String getSistemaOperacional() {
/* 2941 */     return this.sistemaOperacional;
/*      */   }
/*      */   
/*      */   public int getTempoFalha() {
/* 2945 */     return this.tempoFalha;
/*      */   }
/*      */   
/*      */   public Timer getTimerCom() {
/* 2949 */     return this.timerCom;
/*      */   }
/*      */   
/*      */   public Timer getTimerFalha() {
/* 2953 */     return this.timerFalha;
/*      */   }
/*      */   
/*      */   public Timer getTimerShut() {
/* 2957 */     return this.timerShut;
/*      */   }
/*      */   
/*      */   public AbstractUPS getUps() {
/* 2961 */     return this.ups;
/*      */   }
/*      */   
/*      */   public String getUsuario() {
/* 2965 */     return this.usuario;
/*      */   }
/*      */   
/*      */   public int getFatorPotenciaEquipamento() {
/* 2969 */     return this.ups.getFatorPotenciaEquipamento();
/*      */   }
/*      */   
/*      */   public UPSDataObject getUPSDataObject()
/*      */   {
/* 2974 */     return this.ups;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRedeFalha()
/*      */   {
/* 2982 */     return this.redeFalha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] getPortas()
/*      */   {
/* 2991 */     return this.driver.getPortas();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isPrimeiraExecucao()
/*      */   {
/* 3000 */     return this.primeiraExecucao;
/*      */   }
/*      */   
/*      */   public Evento[] getEventos() {
/* 3004 */     return this.eventos;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getIdiomaPersistencia()
/*      */   {
/* 3012 */     return this.persistencia.getIdioma();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBatteryTestAvailable()
/*      */   {
/* 3020 */     return this.ups.isAutoTesteBateriaAvaliable();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHoraTestePersistencia()
/*      */   {
/* 3029 */     return this.persistencia.getHoraTeste();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinutoTestePersistencia()
/*      */   {
/* 3037 */     return this.persistencia.getMinutoTeste();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPeriodoTestePersistencia()
/*      */   {
/* 3046 */     return this.persistencia.getPeriodoTeste();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoTesteEnabledPersitencia()
/*      */   {
/* 3055 */     return this.persistencia.getAutoTesteEnabled();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getModoLoggingPersitencia()
/*      */   {
/* 3064 */     return this.persistencia.getModoLogging();
/*      */   }
/*      */   
/*      */ 
/*      */   public int getEventoPersistencia()
/*      */   {
/* 3070 */     return this.persistencia.getEvento();
/*      */   }
/*      */   
/*      */   public String getScript() {
/* 3074 */     return this.persistencia.getScript();
/*      */   }
/*      */   
/*      */   public boolean[][] getMensagensPersistencia() {
/* 3078 */     return this.persistencia.getMensagens();
/*      */   }
/*      */   
/*      */   public int getExpansorBateriaPersistencia() {
/* 3082 */     return this.persistencia.getExpansorBateria();
/*      */   }
/*      */   
/*      */   public String getNomeUPSPersistencia() {
/* 3086 */     return this.persistencia.getNomeUPS();
/*      */   }
/*      */   
/*      */   public String getEnderecoSMTPPersistencia() {
/* 3090 */     return this.persistencia.getEnderecoSMTP();
/*      */   }
/*      */   
/*      */   public boolean getFlagAutenticacoPersistencia() {
/* 3094 */     return this.persistencia.isFlagAutentic();
/*      */   }
/*      */   
/*      */   public String getUsuarioPersistencia() {
/* 3098 */     return this.persistencia.getUsuario();
/*      */   }
/*      */   
/*      */   public String getSenhaPersistencia() {
/* 3102 */     return this.persistencia.getSenha();
/*      */   }
/*      */   
/*      */   public String getRemetentePersistencia() {
/* 3106 */     return this.persistencia.getRemetente();
/*      */   }
/*      */   
/*      */   public String[] getDestinatariosPersistencia() {
/* 3110 */     return this.persistencia.getDestinatarios();
/*      */   }
/*      */   
/*      */   public int getPortaSMTPPersistencia() {
/* 3114 */     return this.persistencia.getPortaSMTP();
/*      */   }
/*      */   
/*      */   public int getPortaPedidos() {
/* 3118 */     return this.persistencia.getPortaPedidos();
/*      */   }
/*      */   
/*      */   public String getComunidadeLeitura() {
/* 3122 */     return this.persistencia.getComunidadeLeitura();
/*      */   }
/*      */   
/*      */   public String getComunidadeEscrita() {
/* 3126 */     return this.persistencia.getComunidadeEscrita();
/*      */   }
/*      */   
/*      */   public String getEnderecoGerente() {
/* 3130 */     return this.persistencia.getEnderecoGerente();
/*      */   }
/*      */   
/*      */   public int getPortaEnvio() {
/* 3134 */     return this.persistencia.getPortaEnvio();
/*      */   }
/*      */   
/*      */   public int getVSNMP() {
/* 3138 */     return this.persistencia.getVSNMP();
/*      */   }
/*      */   
/*      */   public int getProtocoloTransportePersistencia() {
/* 3142 */     return this.persistencia.getProtocoloTransporte();
/*      */   }
/*      */   
/*      */   public int getAutonoMinima() {
/* 3146 */     return this.persistencia.getAutonoMinima();
/*      */   }
/*      */   
/*      */   public boolean getFlagShutFimAut() {
/* 3150 */     return this.persistencia.isFlagShutFimAut();
/*      */   }
/*      */   
/*      */   public boolean getFlagShutFalhaEletrica() {
/* 3154 */     return this.persistencia.isFlagShutFalhaEletrica();
/*      */   }
/*      */   
/*      */   public int getTempoFalhaEletrica() {
/* 3158 */     return this.persistencia.getTempoFalhaEletrica();
/*      */   }
/*      */   
/*      */   public boolean getModoRemoto() {
/* 3162 */     return this.persistencia.isModoRemoto();
/*      */   }
/*      */   
/*      */   public int getPortaRemota() {
/* 3166 */     return this.persistencia.getPortaRemota();
/*      */   }
/*      */   
/*      */   public String[] getTipoEventos() {
/* 3170 */     return Evento.listaAlarmes;
/*      */   }
/*      */   
/*      */   public String getProvedorEscolhidoPersitencia() {
/* 3174 */     return this.persistencia.getProvedorEscolhido();
/*      */   }
/*      */   
/*      */   public String getProtocoloSNMP() {
/* 3178 */     int i = getProtocoloTransportePersistencia();
/* 3179 */     if (i == 0) {
/* 3180 */       return "UDP";
/*      */     }
/* 3182 */     return "TCP";
/*      */   }
/*      */   
/*      */   public int getVersaoSNMP() {
/* 3186 */     int i = getVSNMP();
/* 3187 */     i++;return i;
/*      */   }
/*      */   
/*      */   public double getValorKw() {
/* 3191 */     return this.persistencia.getValorKW();
/*      */   }
/*      */   
/* 3194 */   public boolean isTemperaturaAvaliable() { return this.ups.isTemperaturaAvaliable(); }
/*      */   
/*      */   public float getPotenciaRealEntrada()
/*      */   {
/* 3198 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public int getFamiliaUPSPersistencia()
/*      */   {
/* 3203 */     return this.persistencia.getFamilia();
/*      */   }
/*      */   
/*      */   public float getPotenciaAparenteEntrada()
/*      */   {
/* 3208 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo) {
/* 3212 */     return this.ups.calculaEstadoBaterias(bateriaInicial, bateriaFinal, Potencia, tempo);
/*      */   }
/*      */   
/*      */   public String getPortaSerial()
/*      */   {
/* 3217 */     return this.persistencia.getPorta();
/*      */   }
/*      */   
/*      */ 
/*      */   public void fimTesteBateria(int resultado)
/*      */   {
/* 3223 */     if (isGui()) {
/* 3224 */       switch (resultado)
/*      */       {
/*      */       case 1: 
/* 3227 */         this.gui.mostraPopUp("ESTADO_BATERIA_OK");
/* 3228 */         break;
/*      */       
/*      */       case 0: 
/* 3231 */         this.gui.mostraPopUp("ESTADO_BATERIA_BOM");
/* 3232 */         break;
/*      */       
/*      */       case 2: 
/* 3235 */         this.gui.mostraPopUp("ESTADO_BATERIA_RUIM");
/*      */       }
/*      */       
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void inicioTesteBateria()
/*      */   {
/* 3246 */     if (isGui()) {
/* 3247 */       this.gui.mostraPopUp("Inicio_Teste_Bateria");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void testeBateriaAbortado(int res)
/*      */   {
/* 3257 */     if (isGui()) {
/* 3258 */       switch (res)
/*      */       {
/*      */       case 1: 
/* 3261 */         this.gui.mostraPopUp("FALHA_TIMEOUT_TESTE_BATERIA");
/* 3262 */         break;
/*      */       
/*      */       case 3: 
/* 3265 */         this.gui.mostraPopUp("FALHA_COMUNICACAO_TESTE_BATERIA");
/* 3266 */         break;
/*      */       
/*      */       case 2: 
/* 3269 */         this.gui.mostraPopUp("FALHA_BATERIA_BAIXA_TESTE_BATERIA");
/*      */       }
/*      */       
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void testeBateriaIndisponivel()
/*      */   {
/* 3280 */     if (isGui()) {
/* 3281 */       this.gui.mostraPopUp("TESTE_BATERIA_NAO_DISPONIVEL");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void salvaPersistenciaSMTP(String destinatario, String endServidor, String porta, String remetente, char[] senha, String usuario, String provedorSmtp, boolean flagAutentic)
/*      */   {
/* 3305 */     String[] dests = { destinatario };
/* 3306 */     this.persistencia.setDestinatarios(dests);
/* 3307 */     this.persistencia.setEnderecoSMTP(endServidor);
/* 3308 */     this.persistencia.setPortaSMTP(Integer.parseInt(porta));
/* 3309 */     this.persistencia.setRemetente(remetente);
/* 3310 */     this.persistencia.setSenha(String.copyValueOf(senha));
/* 3311 */     this.persistencia.setUsuario(usuario);
/* 3312 */     this.persistencia.setProvedorEscolhido(provedorSmtp);
/* 3313 */     this.persistencia.setFlagAutentic(flagAutentic);
/* 3314 */     this.persistencia.salvaConf();
/*      */   }
/*      */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\controle\Controle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */