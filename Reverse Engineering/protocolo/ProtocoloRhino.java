/*      */ package br.com.schneider.sgm.protocolo;
/*      */ 
/*      */ import br.com.schneider.sgm.comunicacao.Communication;
/*      */ import br.com.schneider.sgm.eventos.Evento;
/*      */ import java.util.Calendar;
/*      */ import java.util.Timer;
/*      */ 
/*      */ public class ProtocoloRhino extends ProtocoloUPS
/*      */ {
/*      */   private static final int TEMPO_TIMEOUT = 1000;
/*   11 */   private final int ANO_BASE = 2000;
/*      */   
/*      */   private static final int COMANDO_LIGA_ENTRADA = 1;
/*      */   
/*      */   private static final int COMANDO_DESLIGA_ENTRADA = 2;
/*      */   
/*      */   private static final int COMANDO_LIGA_SAIDA = 3;
/*      */   
/*      */   private static final int COMANDO_DESLIGA_SAIDA = 4;
/*      */   
/*      */   private static final int COMANDO_ATIVA_BYPASS = 5;
/*      */   
/*      */   private static final int COMANDO_DESATIVA_BYPASS = 6;
/*      */   
/*      */   private static final int COMANDO_APAGA_EVENTOS = 8;
/*      */   
/*      */   private static final int COMANDO_CONFIG_IP = 9;
/*      */   
/*      */   private static final int COMANDO_CONFIG_NUMERO_SERIE = 10;
/*      */   
/*      */   private static final int COMANDO_CONFIG_RELOGIO = 11;
/*      */   
/*      */   private static final int COMANDO_CONFIG_TIMER = 12;
/*      */   
/*      */   private static final int COMANDO_CONFIG_AUTO_TESTE = 13;
/*      */   
/*      */   private static final int COMANDO_CONFIG_NOME = 14;
/*      */   
/*      */   private static final int COMANDO_CONFIG_MSGS = 15;
/*      */   
/*      */   private static final int COMANDO_CONFIG_MAC = 16;
/*      */   
/*      */   private static final int COMANDO_DOWNLOAD_EVENTOS = 7;
/*      */   
/*      */   private static final int COMANDO_PACOTE_DADOS = 53;
/*      */   
/*      */   private static final int COMANDO_PACOTE_CONFIGURACAO = 54;
/*      */   
/*      */   private static final int DELAY_ENTRE_BYTES = 3;
/*      */   
/*      */   private static final int NUM_BYTES_PAC_DADOS = 38;
/*      */   
/*      */   private static final int EVENTO_FALHA_REDE = 1;
/*      */   
/*      */   private static final int EVENTO_RETORNO_REDE = 2;
/*      */   
/*      */   private static final int EVENTO_SHUTDOWN_AUT = 4;
/*      */   
/*      */   private static final int EVENTO_SHUTDOWN_COMANDO = 6;
/*      */   
/*      */   private static final int EVENTO_SHUTDOWN_CANCEL = 9;
/*      */   
/*      */   private static final int EVENTO_SOBRECARGA = 10;
/*      */   
/*      */   private static final int EVENTO_CARGA_NOMINAL = 11;
/*      */   
/*      */   private static final int EVENTO_SUPERAQUECIMENTO = 12;
/*      */   
/*      */   private static final int EVENTO_TEMP_NORMAL = 13;
/*      */   
/*      */   private static final int EVENTO_BATERIA_USO = 16;
/*      */   
/*      */   private static final int EVENTO_FIM_BATERIA_USO = 17;
/*      */   
/*      */   private static final int EVENTO_BATERIA_DESCARREGADA = 18;
/*      */   
/*      */   private static final int EVENTO_BATERIA_NORMAL = 19;
/*      */   
/*      */   private static final int EVENTO_BATERIA_CRITICA = 20;
/*      */   
/*      */   private static final int EVENTO_SUBTENSAO_ENTRADA = 22;
/*      */   
/*      */   private static final int EVENTO_SOBRETENSAO_ENTRADA = 23;
/*      */   
/*      */   private static final int EVENTO_DESL_AUS_CON = 25;
/*      */   private static final int EVENTO_SAIDA_LIGADA = 26;
/*      */   private static final int EVENTO_SAIDA_DESLIGADA = 27;
/*      */   private static final int EVENTO_SHUTDOWN_TIMER = 28;
/*      */   private static final int EVENTO_INICIALIZACAO = 30;
/*      */   private static final int EVENTO_BYPASS_ATIVADO = 41;
/*      */   private static final int EVENTO_BYPASS_DESATIVADO = 42;
/*      */   private static final int EVENTO_ENTRADA_LIGADA = 43;
/*      */   private static final int EVENTO_ENTRADA_DESLIGADA = 44;
/*      */   private static final int EVENTO_EVENTOS_APAGADOS = 45;
/*      */   private static final int EVENTO_AUTOTESTE_OK = 46;
/*      */   private static final int EVENTO_AUTOTESTE_FALHOU = 47;
/*      */   private static final int EVENTO_SOBRETENSAO_BOOST = 48;
/*      */   private static final int EVENTO_FALHA_SAIDA = 50;
/*      */   private static final int EVENTO_EM_AUTOTESTE = 52;
/*      */   private int[] vetorBytes;
/*      */   private Timer timeout;
/*      */   private int numeroAlarmesMicrosol;
/*      */   private Evento[] alarmesMicrosol;
/*      */   private int[] pacoteDados;
/*      */   private int versaoFirmware;
/*      */   private int potenciaRealEntrada;
/*      */   private int potenciaAparenteEntrada;
/*      */   private int fatorPotenciaEntrada;
/*      */   private int potenciaRealSaida;
/*      */   private int potenciaAparenteSaida;
/*      */   private int fatorPotenciaSaida;
/*      */   private int rendimento;
/*      */   private int seculo;
/*      */   private boolean estouroRTC;
/*      */   private boolean timerHabilitado;
/*      */   private boolean autoHabilitado;
/*      */   private boolean boostLigado;
/*      */   private boolean bateriaDescarga;
/*      */   private boolean quadraturaAnteriorEntrada;
/*      */   private boolean quadratura;
/*      */   private boolean terminoXModem;
/*      */   private boolean emSincronismo;
/*      */   private boolean transmitirPacote;
/*      */   private boolean executarBeep;
/*      */   private boolean eventoGravando;
/*      */   private boolean redeAnterior;
/*      */   private boolean comandoExecutado;
/*      */   private boolean executarTeste;
/*      */   private boolean quadraturaAnteriorSaida;
/*      */   private boolean comandoSerial;
/*      */   private boolean sobretensao;
/*  132 */   private int ultimoEvento = 0;
/*      */   
/*      */   public ProtocoloRhino() {
/*  135 */     this.timeout = new Timer();
/*  136 */     this.alarmesMicrosol = new Evento[100];
/*  137 */     this.pacoteDados = new int[38];
/*      */   }
/*      */   
/*      */   public boolean ligaEntrada()
/*      */   {
/*  142 */     return enviaComando(1, null);
/*      */   }
/*      */   
/*      */   public boolean desligaEntrada()
/*      */   {
/*  147 */     return enviaComando(2, null);
/*      */   }
/*      */   
/*      */   public boolean ligaSaida()
/*      */   {
/*  152 */     return enviaComando(3, null);
/*      */   }
/*      */   
/*      */   public boolean desligaSaida()
/*      */   {
/*  157 */     return enviaComando(4, null);
/*      */   }
/*      */   
/*      */   public boolean ativaBypass()
/*      */   {
/*  162 */     return enviaComando(5, null);
/*      */   }
/*      */   
/*      */   public boolean desativaBypass()
/*      */   {
/*  167 */     return enviaComando(6, null);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*      */   {
/*  173 */     int[] bytes = new int[19];
/*  174 */     int checksum = 0;int temp = 0;
/*      */     
/*  176 */     bytes[0] = 1;
/*  177 */     bytes[1] = 11;
/*      */     
/*  179 */     bytes[2] = horas;
/*  180 */     bytes[3] = minutos;
/*  181 */     bytes[4] = segundos;
/*      */     
/*  183 */     bytes[5] = getDiaMes();
/*  184 */     bytes[6] = getMes();
/*  185 */     bytes[7] = (getAno() - 2000);
/*  186 */     bytes[8] = 20;
/*  187 */     bytes[9] = (getDiaSemana() + 1);
/*      */     
/*  189 */     bytes[10] = getHoraLigar();
/*  190 */     bytes[11] = getMinutoLigar();
/*  191 */     bytes[12] = getHoraDesligar();
/*  192 */     bytes[13] = getMinutoDesligar();
/*      */     
/*      */ 
/*  195 */     int semana = 0;
/*  196 */     temp = 0;
/*  197 */     boolean[] dias = getDiasSemanaProgramados();
/*      */     
/*  199 */     for (int i = 1; i < 7; i++) {
/*  200 */       if (dias[i] != 0) {
/*  201 */         temp = 1;
/*      */       } else
/*  203 */         temp = 0;
/*  204 */       semana |= temp << 7 - i;
/*      */     }
/*      */     
/*  207 */     bytes[14] = semana;
/*  208 */     bytes[15] = 0;
/*  209 */     bytes[16] = 0;
/*  210 */     bytes[17] = 0;
/*      */     
/*  212 */     for (int i = 1; i <= 17; i++) {
/*  213 */       checksum += bytes[i];
/*      */     }
/*      */     
/*      */ 
/*  217 */     bytes[18] = (checksum % 256);
/*      */     
/*  219 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraCalendario(int diaSemana, int diaMes, int mes, int ano)
/*      */   {
/*  227 */     int[] bytes = new int[19];
/*  228 */     int checksum = 0;int temp = 0;
/*      */     
/*  230 */     bytes[0] = 1;
/*  231 */     bytes[1] = 11;
/*      */     
/*  233 */     bytes[2] = getHora();
/*  234 */     bytes[3] = getMinutos();
/*  235 */     bytes[4] = getSegundos();
/*      */     
/*  237 */     bytes[5] = diaMes;
/*  238 */     bytes[6] = mes;
/*  239 */     bytes[7] = (ano - 2000);
/*  240 */     bytes[8] = 20;
/*  241 */     bytes[9] = (diaSemana + 1);
/*      */     
/*  243 */     bytes[10] = getHoraLigar();
/*  244 */     bytes[11] = getMinutoLigar();
/*  245 */     bytes[12] = getHoraDesligar();
/*  246 */     bytes[13] = getMinutoDesligar();
/*      */     
/*  248 */     int semana = 0;
/*  249 */     boolean[] dias = getDiasSemanaProgramados();
/*  250 */     for (int i = 1; i < 7; i++) {
/*  251 */       if (dias[i] != 0) {
/*  252 */         temp = 1;
/*      */       } else
/*  254 */         temp = 0;
/*  255 */       semana |= temp << 7 - i;
/*      */     }
/*      */     
/*  258 */     bytes[14] = semana;
/*  259 */     bytes[15] = 0;
/*  260 */     bytes[16] = 0;
/*  261 */     bytes[17] = 0;
/*      */     
/*  263 */     for (int i = 1; i <= 17; i++) {
/*  264 */       checksum += bytes[i];
/*      */     }
/*      */     
/*      */ 
/*  268 */     bytes[18] = (checksum % 256);
/*      */     
/*  270 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/*  278 */     int[] bytes = new int[19];
/*  279 */     int checksum = 0;int temp = 0;
/*      */     
/*  281 */     bytes[0] = 1;
/*  282 */     bytes[1] = 11;
/*      */     
/*  284 */     bytes[2] = getHora();
/*  285 */     bytes[3] = getMinutos();
/*  286 */     bytes[4] = getSegundos();
/*      */     
/*  288 */     bytes[5] = this.diaMes;
/*  289 */     bytes[6] = this.mes;
/*  290 */     bytes[7] = (this.ano - 2000);
/*  291 */     bytes[8] = 20;
/*  292 */     bytes[9] = (this.diaSemana + 1);
/*      */     
/*  294 */     bytes[10] = horaLigar;
/*  295 */     bytes[11] = minutoLigar;
/*  296 */     bytes[12] = horaDesligar;
/*  297 */     bytes[13] = minutoDesligar;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  302 */     int semana = 0;
/*      */     
/*  304 */     for (int i = 1; i < 7; i++) {
/*  305 */       if (dias[i] != 0) {
/*  306 */         temp = 1;
/*      */       } else
/*  308 */         temp = 0;
/*  309 */       semana |= temp << 7 - i;
/*      */     }
/*      */     
/*  312 */     bytes[14] = semana;
/*  313 */     bytes[15] = 0;
/*  314 */     bytes[16] = 0;
/*  315 */     bytes[17] = 0;
/*      */     
/*      */ 
/*      */ 
/*  319 */     for (int i = 1; i <= 17; i++) {
/*  320 */       checksum += bytes[i];
/*      */     }
/*      */     
/*      */ 
/*  324 */     bytes[18] = (checksum % 256);
/*      */     
/*      */ 
/*  327 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraCalendarioRelogioSemana(int diaSemana, int diaMes, int mes, int ano, int segundos, int minutos, int horas, boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/*  337 */     int[] bytes = new int[19];
/*  338 */     int checksum = 0;int temp = 0;
/*      */     
/*      */ 
/*  341 */     bytes[0] = 1;
/*  342 */     bytes[1] = 11;
/*      */     
/*  344 */     bytes[2] = horas;
/*  345 */     bytes[3] = minutos;
/*  346 */     bytes[4] = segundos;
/*      */     
/*  348 */     bytes[5] = diaMes;
/*  349 */     bytes[6] = mes;
/*  350 */     bytes[7] = (ano - 2000);
/*  351 */     bytes[8] = 20;
/*  352 */     bytes[9] = (diaSemana + 1);
/*      */     
/*  354 */     bytes[10] = horaLigar;
/*  355 */     bytes[11] = minutoLigar;
/*  356 */     bytes[12] = horaDesligar;
/*  357 */     bytes[13] = minutoDesligar;
/*      */     
/*  359 */     int semana = 0;
/*      */     
/*  361 */     for (int i = 1; i < 7; i++) {
/*  362 */       if (dias[i] != 0) {
/*  363 */         temp = 1;
/*      */       } else
/*  365 */         temp = 0;
/*  366 */       semana |= temp << 7 - i;
/*      */     }
/*      */     
/*  369 */     bytes[14] = semana;
/*  370 */     bytes[15] = 0;
/*  371 */     bytes[16] = 0;
/*  372 */     bytes[17] = 0;
/*      */     
/*  374 */     for (int i = 1; i <= 17; i++) {
/*  375 */       checksum += bytes[i];
/*      */     }
/*      */     
/*      */ 
/*  379 */     bytes[18] = (checksum % 256);
/*      */     
/*      */ 
/*  382 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundos, int minutos, int horas)
/*      */   {
/*  389 */     int[] bytes = new int[19];
/*  390 */     int checksum = 0;int temp = 0;
/*      */     
/*  392 */     bytes[0] = 1;
/*  393 */     bytes[1] = 11;
/*      */     
/*      */ 
/*  396 */     bytes[2] = horas;
/*  397 */     bytes[3] = minutos;
/*  398 */     bytes[4] = segundos;
/*      */     
/*  400 */     bytes[5] = diaMes;
/*  401 */     bytes[6] = mes;
/*  402 */     bytes[7] = (ano - 2000);
/*  403 */     bytes[8] = 20;
/*  404 */     bytes[9] = (diaSemana + 1);
/*      */     
/*  406 */     bytes[10] = getHoraLigar();
/*  407 */     bytes[11] = getMinutoLigar();
/*  408 */     bytes[12] = getHoraDesligar();
/*  409 */     bytes[13] = getMinutoDesligar();
/*      */     
/*      */ 
/*  412 */     int semana = 0;
/*  413 */     temp = 0;
/*  414 */     boolean[] dias = getDiasSemanaProgramados();
/*      */     
/*  416 */     for (int i = 1; i < 7; i++) {
/*  417 */       if (dias[i] != 0) {
/*  418 */         temp = 1;
/*      */       } else
/*  420 */         temp = 0;
/*  421 */       semana |= temp << 7 - i;
/*      */     }
/*      */     
/*  424 */     bytes[14] = semana;
/*  425 */     bytes[15] = 0;
/*  426 */     bytes[16] = 0;
/*  427 */     bytes[17] = 0;
/*      */     
/*      */ 
/*  430 */     for (int i = 1; i <= 17; i++) {
/*  431 */       checksum += bytes[i];
/*      */     }
/*      */     
/*      */ 
/*  435 */     bytes[18] = (checksum % 256);
/*      */     
/*  437 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */   public boolean configuraTimer(int timer)
/*      */   {
/*  442 */     String[] temp = { String.valueOf(timer) };
/*  443 */     return enviaComando(12, temp);
/*      */   }
/*      */   
/*      */   public boolean configuraAutoTeste() {
/*  447 */     return enviaComando(13, null);
/*      */   }
/*      */   
/*      */   public boolean configuraNome(String nome) {
/*  451 */     String[] temp = { nome };
/*  452 */     return enviaComando(14, temp);
/*      */   }
/*      */   
/*      */   public boolean configuraEnvioMensagens() {
/*  456 */     return enviaComando(15, null);
/*      */   }
/*      */   
/*      */   public boolean configuraMAC(String mac) {
/*  460 */     String[] temp = { mac };
/*  461 */     return enviaComando(16, temp);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean shutdown()
/*      */   {
/*  467 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean shutdownReligamento()
/*      */   {
/*  473 */     return false;
/*      */   }
/*      */   
/*      */   public Evento[] downloadEventos()
/*      */   {
/*  478 */     if (this.numeroAlarmesMicrosol > 0) {
/*  479 */       Evento[] temp = (Evento[])this.alarmesMicrosol.clone();
/*  480 */       temp[this.numeroAlarmesMicrosol] = null;
/*  481 */       this.numeroAlarmesMicrosol = 0;
/*      */       
/*  483 */       return temp;
/*      */     }
/*      */     
/*      */ 
/*  487 */     return null;
/*      */   }
/*      */   
/*      */   public boolean apagaEventos()
/*      */   {
/*  492 */     return enviaComando(8, null);
/*      */   }
/*      */   
/*      */   public boolean configuraIP(String endereco) {
/*  496 */     String[] temp = { endereco };
/*  497 */     return enviaComando(9, temp);
/*      */   }
/*      */   
/*      */   public boolean configuraNumeroSerie(String numeroSerie) {
/*  501 */     String[] temp = { numeroSerie };
/*  502 */     return enviaComando(10, temp);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean setModoEvento()
/*      */   {
/*  508 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean setModoContinuo()
/*      */   {
/*  514 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public void trataPacoteDumpping(int numeroEventoAtual)
/*      */   {
/*  520 */     Calendar calendar = new java.util.GregorianCalendar();
/*      */     
/*  522 */     if (numeroEventoAtual != this.ultimoEvento) {
/*  523 */       Evento evento = new Evento(traduzEventos(numeroEventoAtual), calendar.get(11), 
/*  524 */         calendar.get(12), calendar.get(13), 
/*  525 */         calendar.get(5), calendar.get(2) + 1, 
/*  526 */         calendar.get(1));
/*      */       
/*  528 */       this.alarmesMicrosol[this.numeroAlarmesMicrosol] = evento;
/*  529 */       this.numeroAlarmesMicrosol += 1;
/*      */       
/*  531 */       this.ultimoEvento = numeroEventoAtual;
/*      */     }
/*      */   }
/*      */   
/*      */   private String traduzEventos(int numeroEventoAtual) {
/*  536 */     switch (numeroEventoAtual) {
/*      */     case 47: 
/*  538 */       return "AUTO_TESTE_FALHOU";
/*      */     
/*      */     case 46: 
/*  541 */       return "AUTO_TESTE_OK";
/*      */     
/*      */     case 20: 
/*  544 */       return "BATERIA_CRITICA";
/*      */     
/*      */     case 18: 
/*  547 */       return "BATERIA_DESCARREGADA";
/*      */     
/*      */     case 19: 
/*  550 */       return "BATERIA_NORMAL";
/*      */     
/*      */     case 16: 
/*  553 */       return "BATERIA_EM_USO";
/*      */     
/*      */     case 41: 
/*  556 */       return "BYPASS_ATIVADO";
/*      */     
/*      */     case 42: 
/*  559 */       return "BYPASS_DESATIVADO";
/*      */     
/*      */     case 11: 
/*  562 */       return "CARGA_NOMINAL";
/*      */     
/*      */     case 25: 
/*  565 */       return "DESLIGAMENTO_POR_AUSENCIA_DE_CONSUMO";
/*      */     
/*      */     case 44: 
/*  568 */       return "ENTRADA_DESLIGADA";
/*      */     
/*      */     case 43: 
/*  571 */       return "ENTRADA_LIGADA";
/*      */     
/*      */     case 45: 
/*  574 */       return "EVENTOS_APAGADOS";
/*      */     
/*      */     case 1: 
/*  577 */       return "FALHA_NA_REDE";
/*      */     
/*      */     case 17: 
/*  580 */       return "FIM_DO_USO_DA_BATERIA";
/*      */     
/*      */     case 30: 
/*  583 */       return "AUTO_TESTE_FALHOU";
/*      */     
/*      */     case 2: 
/*  586 */       return "RETORNO_DA_REDE";
/*      */     
/*      */     case 27: 
/*  589 */       return "SAIDA_DESLIGADA";
/*      */     
/*      */     case 26: 
/*  592 */       return "SAIDA_LIGADA";
/*      */     
/*      */     case 4: 
/*  595 */       return "SHUTDOWN_POR_AUTONOMIA";
/*      */     
/*      */     case 9: 
/*  598 */       return "SHUTDOWN_CANCELADO";
/*      */     
/*      */     case 6: 
/*  601 */       return "SHUTDOWN_POR_COMANDO";
/*      */     
/*      */     case 28: 
/*  604 */       return "SHUTDOWN_POR_TIMER";
/*      */     
/*      */     case 10: 
/*  607 */       return "SOBRECARGA";
/*      */     
/*      */     case 23: 
/*  610 */       return "SOBRETENSAO_NA_ENTRADA";
/*      */     
/*      */     case 48: 
/*  613 */       return "SOBRETENSAO_NO_BOOST";
/*      */     
/*      */     case 22: 
/*  616 */       return "SUBTENSAO_NA_ENTRADA";
/*      */     
/*      */     case 12: 
/*  619 */       return "SUPER_AQUECIMENTO";
/*      */     
/*      */     case 13: 
/*  622 */       return "TEMPERATURA_NORMAL";
/*      */     
/*      */     case 50: 
/*  625 */       return "FALHA_NA_SAIDA";
/*      */     
/*      */     case 52: 
/*  628 */       return "EM_AUTOTESTE";
/*      */     }
/*      */     
/*  631 */     return "EVENTO_DESCONHECIDO";
/*      */   }
/*      */   
/*      */ 
/*      */   protected void trataPacoteDados()
/*      */   {
/*  637 */     setCabecalhoPacote(this.pacoteDados[0]);
/*  638 */     setversaoFirmware(this.pacoteDados[1]);
/*  639 */     setTensaoEntrada(this.pacoteDados[2]);
/*  640 */     setCorrenteEntrada(this.pacoteDados[3]);
/*  641 */     setPotenciaRealEntrada(this.pacoteDados[4], this.pacoteDados[5]);
/*  642 */     setPotenciaAparenteEntrada(this.pacoteDados[6], this.pacoteDados[7]);
/*  643 */     setFatorPotenciaEntrada(this.pacoteDados[8]);
/*  644 */     setFrequenciaEntrada(this.pacoteDados[9], this.pacoteDados[10]);
/*  645 */     setTensaoSaida(this.pacoteDados[11]);
/*  646 */     setCorrenteSaida(this.pacoteDados[12]);
/*  647 */     setPotenciaRealSaida(this.pacoteDados[13], this.pacoteDados[14]);
/*  648 */     setPotenciaReal(this.pacoteDados[13] + 256 * this.pacoteDados[14]);
/*  649 */     setPotenciaAparenteSaida(this.pacoteDados[15], this.pacoteDados[16]);
/*  650 */     setPotenciaAparente(this.pacoteDados[15] + 256 * this.pacoteDados[16]);
/*  651 */     setFatorPotenciaSaida(this.pacoteDados[17]);
/*  652 */     setFrequenciaSaida(this.pacoteDados[18], this.pacoteDados[19]);
/*  653 */     setTensaoBateria(this.pacoteDados[20]);
/*  654 */     setTensaoBoost(this.pacoteDados[21], this.pacoteDados[22]);
/*  655 */     setTemperaturaUPS(this.pacoteDados[23]);
/*  656 */     setRendimento(this.pacoteDados[24]);
/*  657 */     trataPacoteDumpping(this.pacoteDados[25]);
/*  658 */     setHora(this.pacoteDados[26]);
/*  659 */     setMinutos(this.pacoteDados[27]);
/*  660 */     setSegundos(this.pacoteDados[28]);
/*  661 */     setDiaMes(this.pacoteDados[29]);
/*  662 */     setMes(this.pacoteDados[30]);
/*  663 */     setAno(this.pacoteDados[31]);
/*  664 */     setSeculo(this.pacoteDados[32]);
/*  665 */     setEstouroRTC(this.pacoteDados[33]);
/*  666 */     setSaidaLigada(this.pacoteDados[33]);
/*  667 */     setEntradaLigada(this.pacoteDados[33]);
/*  668 */     setBypassAtivado(this.pacoteDados[33]);
/*  669 */     setAutoHabilitado(this.pacoteDados[33]);
/*  670 */     setTimerHabilitado(this.pacoteDados[33]);
/*  671 */     setBoostLigado(this.pacoteDados[33]);
/*  672 */     setBateriaDescarga(this.pacoteDados[33]);
/*  673 */     setQuadraturaAnteriorEntrada(this.pacoteDados[34]);
/*  674 */     setQuadratura(this.pacoteDados[34]);
/*  675 */     setTerminoXModem(this.pacoteDados[34]);
/*  676 */     setEmSincronismo(this.pacoteDados[34]);
/*  677 */     setTransmitirPacote(this.pacoteDados[34]);
/*  678 */     setExecutarBeep(this.pacoteDados[34]);
/*  679 */     setEventoGravando(this.pacoteDados[35]);
/*  680 */     setModoRede(this.pacoteDados[35]);
/*  681 */     setRedeAnterior(this.pacoteDados[35]);
/*  682 */     setComandoExecutado(this.pacoteDados[35]);
/*      */     
/*  684 */     setTensaoSaidaNominal(this.pacoteDados[34]);
/*  685 */     setQuadraturaAnteriorSaida(this.pacoteDados[35]);
/*  686 */     setComandoSerial(this.pacoteDados[35]);
/*  687 */     setsobreTensao(this.pacoteDados[35]);
/*      */     
/*  689 */     if (this.ouvinteProtocolo != null) {
/*  690 */       this.ouvinteProtocolo.notifica();
/*      */     }
/*      */   }
/*      */   
/*      */   private void setversaoFirmware(int versao) {
/*  695 */     this.versaoFirmware = versao;
/*      */   }
/*      */   
/*      */   private void setPotenciaRealEntrada(int potencia1, int potencia2) {
/*  699 */     this.potenciaRealEntrada = (potencia1 + potencia2 * 256);
/*      */   }
/*      */   
/*      */   private void setPotenciaAparenteEntrada(int potencia1, int potencia2) {
/*  703 */     this.potenciaAparenteEntrada = (potencia1 + potencia2 * 256);
/*      */   }
/*      */   
/*      */   private void setFatorPotenciaEntrada(int fatorPotencia) {
/*  707 */     this.fatorPotenciaEntrada = fatorPotencia;
/*      */   }
/*      */   
/*      */   private void setFrequenciaEntrada(int frequencia1, int frequencia2) {
/*  711 */     this.frequenciaEntrada = ((frequencia1 + frequencia2 * 256) / 10);
/*      */   }
/*      */   
/*      */   private void setPotenciaRealSaida(int potencia1, int potencia2) {
/*  715 */     this.potenciaRealSaida = (potencia1 + potencia2 * 256);
/*  716 */     this.potenciaReal = this.potenciaRealSaida;
/*      */   }
/*      */   
/*      */   private void setPotenciaAparenteSaida(int potencia1, int potencia2)
/*      */   {
/*  721 */     this.potenciaAparenteSaida = (potencia1 + potencia2 * 256);
/*  722 */     this.potenciaAparente = this.potenciaAparenteSaida;
/*      */   }
/*      */   
/*  725 */   private void setFatorPotenciaSaida(int fatorPotencia) { this.fatorPotenciaSaida = fatorPotencia;
/*  726 */     this.fatorPotenciaCarga = this.fatorPotenciaSaida;
/*      */   }
/*      */   
/*      */   private void setFrequenciaSaida(int frequencia1, int frequencia2) {
/*  730 */     this.frequenciaSaida = ((frequencia1 + frequencia2 * 256) / 10);
/*      */   }
/*      */   
/*      */   private void setTensaoBoost(int tensao1, int tensao2) {
/*  734 */     this.tensaoBoost = (tensao1 + tensao2 * 256);
/*      */   }
/*      */   
/*      */   private void setRendimento(int rendimento) {
/*  738 */     this.rendimento = rendimento;
/*      */   }
/*      */   
/*      */   private void setSeculo(int seculo) {
/*  742 */     this.seculo = seculo;
/*      */   }
/*      */   
/*      */   private void setEstouroRTC(int flag) {
/*  746 */     this.estouroRTC = ((flag & 0x1) == 1);
/*      */   }
/*      */   
/*      */   private void setTimerHabilitado(int flag) {
/*  750 */     this.timerHabilitado = ((flag & 0x20) == 32);
/*      */   }
/*      */   
/*      */   private void setAutoHabilitado(int flag) {
/*  754 */     this.autoHabilitado = ((flag & 0x10) == 16);
/*      */   }
/*      */   
/*      */   private void setBoostLigado(int flag) {
/*  758 */     this.boostLigado = ((flag & 0x40) == 64);
/*      */   }
/*      */   
/*      */   private void setBateriaDescarga(int flag) {
/*  762 */     this.bateriaDescarga = ((flag & 0x80) == 128);
/*      */   }
/*      */   
/*      */   private void setQuadraturaAnteriorEntrada(int flag) {
/*  766 */     this.quadraturaAnteriorEntrada = ((flag & 0x1) == 1);
/*      */   }
/*      */   
/*      */   private void setQuadratura(int flag) {
/*  770 */     this.quadratura = ((flag & 0x2) == 2);
/*      */   }
/*      */   
/*      */   private void setTerminoXModem(int flag) {
/*  774 */     this.terminoXModem = ((flag & 0x4) == 4);
/*      */   }
/*      */   
/*      */   private void setEmSincronismo(int flag) {
/*  778 */     this.emSincronismo = ((flag & 0x8) == 8);
/*      */   }
/*      */   
/*      */   private void setTransmitirPacote(int flag) {
/*  782 */     this.transmitirPacote = ((flag & 0x10) == 16);
/*      */   }
/*      */   
/*      */   private void setExecutarBeep(int flag) {
/*  786 */     this.executarBeep = ((flag & 0x20) == 32);
/*      */   }
/*      */   
/*      */   private void setEventoGravando(int flag) {
/*  790 */     this.eventoGravando = ((flag & 0x1) == 1);
/*      */   }
/*      */   
/*      */   private void setRedeAnterior(int flag) {
/*  794 */     this.redeAnterior = ((flag & 0x4) == 4);
/*      */   }
/*      */   
/*      */   private void setComandoExecutado(int flag) {
/*  798 */     this.comandoExecutado = ((flag & 0x8) == 8);
/*      */   }
/*      */   
/*      */   private void setExecutarTeste(int flag) {
/*  802 */     this.executarTeste = ((flag & 0x10) == 16);
/*      */   }
/*      */   
/*      */   private void setQuadraturaAnteriorSaida(int flag)
/*      */   {
/*  807 */     this.quadraturaAnteriorSaida = ((flag & 0x20) == 32);
/*      */   }
/*      */   
/*      */   private void setComandoSerial(int flag) {
/*  811 */     this.comandoSerial = ((flag & 0x40) == 64);
/*      */   }
/*      */   
/*      */   private void setsobreTensao(int flag) {
/*  815 */     this.sobretensao = ((flag & 0x80) == 128);
/*      */   }
/*      */   
/*      */   public void recebeDados()
/*      */   {
/*  820 */     this.vetorBytes = this.comunicador.getBytes();
/*  821 */     int i = 0;int checksum = 0;
/*      */     
/*  823 */     while (this.vetorBytes[(i++)] != -1) {}
/*  824 */     int numeroBytes = i - 2;
/*      */     
/*      */ 
/*  827 */     for (i = 0; i < 38; i++) {
/*  828 */       this.pacoteDados[i] = this.vetorBytes[i];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  835 */     switch (numeroBytes)
/*      */     {
/*      */     case 1: 
/*      */       break;
/*      */     
/*      */ 
/*      */     case 37: 
/*      */     case 38: 
/*  843 */       checksum = 0;
/*  844 */       for (i = 0; i < 36; i++)
/*  845 */         checksum += this.pacoteDados[i];
/*  846 */       checksum %= 256;
/*      */       
/*      */ 
/*      */ 
/*  850 */       if (this.pacoteDados[36] == checksum)
/*      */       {
/*  852 */         trataPacoteDados();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  859 */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean enviaDados(int[] pacote)
/*      */   {
/*  870 */     this.comunicador.sendBytes(pacote, 3);
/*      */     
/*  872 */     this.esperaACK = false;
/*  873 */     return true;
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
/*      */   public void setAno(int ano)
/*      */   {
/*  889 */     this.ano = ano;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAutonomiaBateria(int autonomiaBateria)
/*      */   {
/*  895 */     this.autonomiaBateria = autonomiaBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaBaixa(int bateriaBaixa) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaCarregada(int bateriaCarregada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaCritica(int bateriaCritica) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaDescarregada(int bateriaDescarregada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBypassAtivado(int bypassAtivado)
/*      */   {
/*  925 */     this.bypassAtivado = ((bypassAtivado & 0x8) == 8);
/*  926 */     this.modoBypass = ((bypassAtivado & 0x8) == 8);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCargaElevada(int cargaElevada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCarregandoBateria(int carregandoBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setComandoAceito(int comandoAceito) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCorrenteEntrada(int correnteEntrada)
/*      */   {
/*  950 */     this.correnteEntrada = correnteEntrada;
/*      */   }
/*      */   
/*      */   public void setCorrenteSaida(int correnteSaida)
/*      */   {
/*  955 */     this.correnteSaida = correnteSaida;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDiaMes(int diaMes)
/*      */   {
/*  961 */     this.diaMes = diaMes;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDiaSemana(int diaSemana)
/*      */   {
/*  967 */     this.diaSemana = diaSemana;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFatorPotenciaCarga(int fatorPotenciaCarga)
/*      */   {
/*  973 */     this.fatorPotenciaCarga = fatorPotenciaCarga;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFrequenciaEntrada(int frequenciaEntrada)
/*      */   {
/*  979 */     this.frequenciaEntrada = frequenciaEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFrequenciaSaida(int frequenciaSaida)
/*      */   {
/*  985 */     this.frequenciaSaida = frequenciaSaida;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHora(int hora)
/*      */   {
/*  991 */     this.hora = hora;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHoraDesligar(int horaDesligar)
/*      */   {
/*  997 */     this.horaDesligar = horaDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHoraLigar(int horaLigar)
/*      */   {
/* 1003 */     this.horaLigar = horaLigar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*      */   {
/* 1009 */     this.limiteInferiorTensaoEntrada = limiteInferiorTensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*      */   {
/* 1015 */     this.limiteInferiorTensaoSaida = limiteInferiorTensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*      */   {
/* 1021 */     this.limiteSuperiorTensaoEntrada = limiteSuperiorTensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*      */   {
/* 1027 */     this.limiteSuperiorTensaoSaida = limiteSuperiorTensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMes(int mes)
/*      */   {
/* 1033 */     this.mes = mes;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutoDesligar(int minutoDesligar)
/*      */   {
/* 1039 */     this.minutoDesligar = minutoDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutoLigar(int minutoLigar)
/*      */   {
/* 1045 */     this.minutoLigar = minutoLigar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutos(int minutos)
/*      */   {
/* 1051 */     this.minutos = minutos;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setPercentualBateria(int percentualBateria)
/*      */   {
/* 1057 */     this.percentualBateria = percentualBateria;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setPotenciaAparente(int potenciaAparente)
/*      */   {
/* 1063 */     this.potenciaAparente = potenciaAparente;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setPotenciaReal(int potenciaReal)
/*      */   {
/* 1069 */     this.potenciaReal = potenciaReal;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSegundos(int segundos)
/*      */   {
/* 1075 */     this.segundos = segundos;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTemperaturaUPS(int temperaturaUPS)
/*      */   {
/* 1081 */     this.temperaturaUPS = temperaturaUPS;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTensaoBateria(int tensaoBateria)
/*      */   {
/* 1087 */     this.tensaoBateria = tensaoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTensaoBoost(int tensaoBoost)
/*      */   {
/* 1093 */     this.tensaoBoost = tensaoBoost;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada(int tensaoEntrada)
/*      */   {
/* 1100 */     this.tensaoEntrada = tensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTensaoEntradaNominal(int tensaoEntradaNominal)
/*      */   {
/* 1106 */     this.tensaoEntradaNominal = tensaoEntradaNominal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoSaida(int tensaoSaida)
/*      */   {
/* 1113 */     if (isSaidaLigada())
/*      */     {
/* 1115 */       if (this.tensaoSaidaNominal == 220) {
/* 1116 */         this.tensaoSaida = (2 * (tensaoSaida - 5));
/*      */       } else {
/* 1118 */         this.tensaoSaida = (tensaoSaida + 6);
/*      */       }
/*      */     } else {
/* 1121 */       this.tensaoSaida = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setTensaoSaidaNominal(int tensaoSaidaNominal)
/*      */   {
/* 1127 */     if ((tensaoSaidaNominal & 0x10) == 16)
/*      */     {
/* 1129 */       this.tensaoSaidaNominal = 110;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1134 */       this.tensaoSaidaNominal = 220;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCabecalhoPacote(int cabecalhoPacote)
/*      */   {
/* 1141 */     this.cabecalhoPacote = cabecalhoPacote;
/*      */   }
/*      */   
/*      */ 
/*      */   public void pedidoDados()
/*      */   {
/* 1147 */     enviaComando(53, null);
/*      */   }
/*      */   
/*      */   public void pedidoConfiguracoes() {
/* 1151 */     enviaComando(54, null);
/*      */   }
/*      */   
/*      */   private boolean enviaComando(int comando, String[] dadosAdicionais) {
/* 1155 */     int[] pacoteComando = new int[19];
/* 1156 */     int checksoma = 0;
/*      */     
/*      */ 
/*      */ 
/* 1160 */     switch (comando)
/*      */     {
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/*      */     case 9: 
/*      */     case 10: 
/*      */     case 12: 
/*      */     case 13: 
/*      */     case 14: 
/*      */     case 15: 
/*      */     case 16: 
/*      */     case 53: 
/* 1178 */       pacoteComando[0] = 1;
/* 1179 */       pacoteComando[1] = comando;
/*      */       
/* 1181 */       for (int i = 2; i < 18; i++) {
/* 1182 */         pacoteComando[i] = 0;
/*      */       }
/* 1184 */       for (int i = 1; i < 18; i++) {
/* 1185 */         checksoma += pacoteComando[i];
/*      */       }
/* 1187 */       pacoteComando[18] = checksoma;
/*      */       
/* 1189 */       int i = 0; if (i >= 19) {
/*      */         break;
/*      */       }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 11: 
/*      */     case 54: 
/*      */       try
/*      */       {
/* 1200 */         pacoteComando[0] = 207;
/*      */         
/* 1202 */         for (int j = 1; j < 7; j++) {
/* 1203 */           pacoteComando[j] = Integer.valueOf(dadosAdicionais[(j - 1)]).intValue();
/*      */         }
/* 1205 */         pacoteComando[8] = (Integer.valueOf(dadosAdicionais[7]).intValue() << 5);
/* 1206 */         pacoteComando[8] |= Integer.valueOf(dadosAdicionais[8]).intValue();
/* 1207 */         pacoteComando[9] = (Integer.valueOf(dadosAdicionais[9]).intValue() << 4);
/* 1208 */         pacoteComando[9] |= Integer.valueOf(dadosAdicionais[10]).intValue() - 2;
/* 1209 */         pacoteComando[10] = (Integer.valueOf(dadosAdicionais[11]).intValue() & 0x80);
/* 1210 */         pacoteComando[11] = 0;
/*      */         
/* 1212 */         for (int i = 0; i < 11; i++) {
/* 1213 */           pacoteComando[11] += pacoteComando[i];
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {}
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
/* 1229 */     return enviaDados(pacoteComando);
/*      */   }
/*      */   
/*      */   public boolean pedidoDumpping()
/*      */   {
/* 1234 */     return false;
/*      */   }
/*      */   
/* 1237 */   public void notifica() { recebeDados(); }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void main(String[] args) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDiasSemanaProgramados(int diasSemanaProgramados) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEntradaLigada(int entradaLigada)
/*      */   {
/* 1254 */     this.entradaLigada = ((entradaLigada & 0x4) == 4);
/* 1255 */     if ((this.tensaoEntrada >= 210) && (this.tensaoEntrada <= 230)) {
/* 1256 */       setTensaoEntrada220(true);
/*      */     } else {
/* 1258 */       setTensaoEntrada220(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExpansorBateria(int expansorBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setModeloUPS(int modeloUPS) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoBateria(int modoBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoBypass(int modoBypass) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoRede(int modoRede)
/*      */   {
/* 1284 */     this.modoRede = ((modoRede & 0x2) != 2);
/*      */     
/* 1286 */     if (!this.modoRede)
/*      */     {
/* 1288 */       if (isSaidaLigada()) {
/* 1289 */         this.usandoSomenteBateria = true;
/*      */       }
/*      */     }
/*      */     else {
/* 1293 */       this.usandoSomenteBateria = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNumeroTimeout(int numeroTimeout) {}
/*      */   
/*      */ 
/*      */   public void setSaidaLigada(int saidaLigada)
/*      */   {
/* 1304 */     this.saidaLigada = ((saidaLigada & 0x2) == 2);
/*      */     
/* 1306 */     if ((this.tensaoSaida >= 210) && (this.tensaoSaida <= 230)) {
/* 1307 */       setTensaoSaida220(true);
/*      */     } else {
/* 1309 */       setTensaoSaida220(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSobrecarga(int sobrecarga) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSuperAquecimento(int superAquecimento) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTemperaturaElevada(int temperaturaElevada) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*      */   {
/* 1329 */     this.tensaoEntrada220 = tensaoEntrada220;
/*      */   }
/*      */   
/*      */   public void setTensaoSaida220(boolean tensaoSaida220)
/*      */   {
/* 1334 */     this.tensaoSaida220 = tensaoSaida220;
/*      */   }
/*      */   
/*      */   public void setTensaoEntrada220(int tensaoEntrada220) {}
/*      */   
/*      */   public void setTensaoSaida220(int tensaoSaida220) {}
/*      */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\protocolo\ProtocoloRhino.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */