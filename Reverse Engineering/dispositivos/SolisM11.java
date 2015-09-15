/*      */ package br.com.schneider.sgm.dispositivos;
/*      */ 
/*      */ import br.com.schneider.sgm.eventos.UPSListener;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloSolis;
/*      */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*      */ import br.com.schneider.sgm.ups.AbstractUPS;
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
/*      */ public class SolisM11
/*      */   extends AbstractUPS
/*      */ {
/*      */   protected int contaAutonomiasBaixas;
/*      */   protected float temperaturaCritica;
/*      */   protected float frequenciaEntradaC;
/*      */   protected float tensaoSaida220F1MB;
/*      */   protected float tensaoSaida220F2MB;
/*      */   protected float tensaoSaida220F1MR;
/*      */   protected float tensaoSaida220F2MR;
/*      */   protected float tensaoSaida110F1MB;
/*      */   protected float tensaoSaida110F2MB;
/*      */   protected float tensaoSaida110F1MR;
/*      */   protected float tensaoSaida110F2MR;
/*      */   protected float tensaoEntradaOffset;
/*      */   protected int comparadorTensaoEntrada;
/*      */   protected float tensaoEntradaF1193;
/*      */   protected float tensaoEntradaF2193;
/*      */   protected float tensaoEntradaF1194;
/*      */   protected float tensaoEntradaF2194;
/*      */   protected float tensaoBateriaF1;
/*      */   protected float tensaoBateriaF2;
/*      */   protected float correnteSaida220F1MB;
/*      */   protected float correnteSaida220F2MB;
/*      */   protected float correnteSaida220F1MR;
/*      */   protected float correnteSaida220F2MR;
/*      */   protected float correnteSaida110F1MB;
/*      */   protected float correnteSaida110F2MB;
/*      */   protected float correnteSaida110F1MR;
/*      */   protected float correnteSaida110F2MR;
/*      */   protected float potenciaAparenteOffset;
/*      */   protected float potenciaAparente220F1MR;
/*      */   protected float potenciaAparente220F2MR;
/*      */   protected float potenciaAparente220F1MB;
/*      */   protected float potenciaAparente220F2MB;
/*      */   protected float potenciaAparente110F1MR;
/*      */   protected float potenciaAparente110F2MR;
/*      */   protected float potenciaAparente110F1MB;
/*      */   protected float potenciaAparente110F2MB;
/*      */   protected float potenciaReal220F1MR;
/*      */   protected float potenciaReal220F2MR;
/*      */   protected float potenciaReal220F1MB;
/*      */   protected float potenciaReal220F2MB;
/*      */   protected float potenciaReal110F1MR;
/*      */   protected float potenciaReal110F2MR;
/*      */   protected float potenciaReal110F1MB;
/*      */   protected float potenciaReal110F2MB;
/*      */   protected float correnteEntradaF1MR;
/*      */   protected float correnteEntradaF2MR;
/*      */   protected float bateriaMaximoMR;
/*      */   protected float bateriaMinimoMR;
/*      */   protected float bateriaMaximoMB;
/*      */   protected float bateriaMinimoMB;
/*      */   protected int comparadorAutonomiaBateria;
/*      */   protected int comparadorContadorAutonomiaBaixa;
/*  129 */   protected int[] calcAut0100 = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 11, 12, 15, 17, 19, 22, 25, 28, 31, 35, 40, 43, 47, 52, 59, 64, 68, 75 };
/*      */   
/*  131 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 18, 20, 21, 24, 28, 30, 32, 33, 33, 34 };
/*      */   
/*  133 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 21, 22, 23 };
/*      */   
/*  135 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9, 19, 11, 12, 13, 14, 16, 18, 20 };
/*      */   
/*  137 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 10, 11, 12, 13, 14 };
/*      */   
/*  139 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 6, 7, 7, 8, 9, 10, 11, 12, 12, 12 };
/*      */   
/*  141 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9, 10, 10, 10, 10, 10, 10 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SolisM11(ProtocoloUPS protocolo)
/*      */   {
/*  151 */     setProtocoloUPS(protocolo);
/*  152 */     protocolo.setModeloUPS(11);
/*  153 */     instanciaGlobais();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SolisM11()
/*      */   {
/*  163 */     instanciaGlobais();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void instanciaGlobais()
/*      */   {
/*  172 */     this.temperaturaCritica = 55.0F;
/*  173 */     this.potenciaNominalVA = 1000;
/*  174 */     this.potenciaNominalW = 1000;
/*  175 */     this.frequenciaEntradaC = 101715.0F;
/*  176 */     this.tensaoSaida220F1MB = 3.15F;
/*  177 */     this.tensaoSaida220F2MB = 2.0F;
/*  178 */     this.tensaoSaida220F1MR = 2.9F;
/*  179 */     this.tensaoSaida220F2MR = 13.0F;
/*  180 */     this.tensaoSaida110F1MB = 1.4F;
/*  181 */     this.tensaoSaida110F2MB = 18.0F;
/*  182 */     this.tensaoSaida110F1MR = 1.44F;
/*  183 */     this.tensaoSaida110F2MR = 13.0F;
/*  184 */     this.tensaoEntradaOffset = 30.0F;
/*  185 */     this.comparadorTensaoEntrada = 194;
/*  186 */     this.tensaoEntradaF1193 = 1.141F;
/*  187 */     this.tensaoEntradaF2193 = 12.0F;
/*  188 */     this.tensaoEntradaF1194 = 2.5F;
/*  189 */     this.tensaoEntradaF2194 = -250.0F;
/*  190 */     this.tensaoBateriaF1 = 0.13908206F;
/*  191 */     this.tensaoBateriaF2 = 0.6F;
/*  192 */     this.correnteSaida220F1MB = 0.020408163F;
/*  193 */     this.correnteSaida220F2MB = 0.1F;
/*  194 */     this.correnteSaida220F1MR = 0.020408163F;
/*  195 */     this.correnteSaida220F2MR = 0.1F;
/*  196 */     this.correnteSaida110F1MB = 0.04597701F;
/*  197 */     this.correnteSaida110F2MB = 0.15F;
/*  198 */     this.correnteSaida110F1MR = 0.04597701F;
/*  199 */     this.correnteSaida110F2MR = 0.15F;
/*  200 */     this.potenciaAparenteOffset = 28.0F;
/*  201 */     this.potenciaAparente220F1MR = 0.064935066F;
/*  202 */     this.potenciaAparente220F2MR = 22.0F;
/*  203 */     this.potenciaAparente220F1MB = 0.064935066F;
/*  204 */     this.potenciaAparente220F2MB = 22.0F;
/*  205 */     this.potenciaAparente110F1MR = 0.074074075F;
/*  206 */     this.potenciaAparente110F2MR = 16.0F;
/*  207 */     this.potenciaAparente110F1MB = 0.07751938F;
/*  208 */     this.potenciaAparente110F2MB = 17.0F;
/*  209 */     this.potenciaReal220F1MR = 0.06896552F;
/*  210 */     this.potenciaReal220F2MR = 20.0F;
/*  211 */     this.potenciaReal220F1MB = 0.071428575F;
/*  212 */     this.potenciaReal220F2MB = 25.0F;
/*  213 */     this.potenciaReal110F1MR = 0.07770008F;
/*  214 */     this.potenciaReal110F2MR = 15.6F;
/*  215 */     this.potenciaReal110F1MB = 0.08196721F;
/*  216 */     this.potenciaReal110F2MB = 13.0F;
/*  217 */     this.correnteEntradaF1MR = 22.2F;
/*  218 */     this.correnteEntradaF2MR = 800.0F;
/*  219 */     this.bateriaMaximoMR = 27.0F;
/*  220 */     this.bateriaMinimoMR = 23.0F;
/*  221 */     this.bateriaMaximoMB = 26.0F;
/*  222 */     this.bateriaMinimoMB = 21.0F;
/*  223 */     this.comparadorAutonomiaBateria = 5;
/*  224 */     this.comparadorContadorAutonomiaBaixa = 50;
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
/*      */   public boolean ligaEntrada()
/*      */   {
/*  239 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desligaEntrada()
/*      */   {
/*  245 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ligaSaida()
/*      */   {
/*  251 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desligaSaida()
/*      */   {
/*  257 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ativaBypass()
/*      */   {
/*  263 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desativaBypass()
/*      */   {
/*  269 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean iniciarAutoteste()
/*      */   {
/*  275 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean finalizarAutoteste()
/*      */   {
/*  281 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean shutdown()
/*      */   {
/*  287 */     return this.protocoloUPS.shutdown();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean shutdownReligamento()
/*      */   {
/*  293 */     return this.protocoloUPS.shutdownReligamento();
/*      */   }
/*      */   
/*      */ 
/*      */   public void downloadEventos()
/*      */   {
/*  299 */     this.eventos = this.protocoloUPS.downloadEventos();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*      */   {
/*  310 */     return this.protocoloUPS.configuraRelogio(segundos, minutos, horas);
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
/*      */   public boolean configuraCalendario(int diaSemana, int diaMes, int mes, int ano)
/*      */   {
/*  324 */     return this.protocoloUPS.configuraCalendario(diaSemana, diaMes, mes, ano);
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
/*      */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*      */   {
/*  341 */     return this.protocoloUPS.configuraCalendarioRelogio(diaSemana, diaMes, mes, ano, segundo, minuto, hora);
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
/*      */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/*  356 */     return this.protocoloUPS.programaSemana(dias, horaLigar, minutoLigar, horaDesligar, minutoDesligar);
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
/*      */ 
/*      */ 
/*      */   public boolean programa(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar, int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*      */   {
/*  381 */     return this.protocoloUPS.configuraCalendarioRelogioSemana(diaSemana, diaMes, mes, ano, segundo, minuto, hora, dias, horaLigar, minutoLigar, 
/*  382 */       horaDesligar, minutoDesligar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addUPSListener(UPSListener ouvinteUPS)
/*      */   {
/*  391 */     this.ouvinteUPS = ouvinteUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notifica()
/*      */   {
/*  399 */     setModoRede(this.protocoloUPS.getModoRede());
/*  400 */     setTensaoEntrada220(this.protocoloUPS.isTensaoEntrada220());
/*  401 */     setTensaoEntradaNominal(this.protocoloUPS.getTensaoEntradaNominal());
/*  402 */     setTensaoEntrada(this.protocoloUPS.getTensaoEntrada());
/*  403 */     setTensaoBateria(this.protocoloUPS.getTensaoBateria());
/*  404 */     setTensaoSaida220(this.protocoloUPS.isTensaoSaida220());
/*  405 */     setPotenciaAparente(this.protocoloUPS.getPotenciaAparente());
/*  406 */     setCorrenteSaida(this.protocoloUPS.getCorrenteSaida());
/*  407 */     setModoBateria(this.protocoloUPS.getModoBateria());
/*  408 */     setTensaoSaida(this.protocoloUPS.getTensaoSaida());
/*  409 */     setCorrenteEntrada(this.protocoloUPS.getCorrenteEntrada());
/*  410 */     setFrequenciaEntrada(this.protocoloUPS.getFrequenciaEntrada());
/*  411 */     setLimiteInferiorTensaoEntrada(this.protocoloUPS.getLimiteInferiorTensaoEntrada());
/*  412 */     setLimiteSuperiorTensaoEntrada(this.protocoloUPS.getLimiteSuperiorTensaoEntrada());
/*  413 */     setTensaoSaidaNominal(this.protocoloUPS.getTensaoSaidaNominal());
/*  414 */     setFrequenciaSaida(this.protocoloUPS.getFrequenciaSaida());
/*  415 */     setLimiteInferiorTensaoSaida(this.protocoloUPS.getLimiteInferiorTensaoSaida());
/*  416 */     setLimiteSuperiorTensaoSaida(this.protocoloUPS.getLimiteSuperiorTensaoSaida());
/*  417 */     setPotenciaReal(this.protocoloUPS.getPotenciaReal());
/*  418 */     setFatorPotenciaCarga(this.protocoloUPS.getFatorPotenciaCarga());
/*  419 */     setTensaoBoost(this.protocoloUPS.getTensaoBoost());
/*  420 */     setTemperaturaUPS(this.protocoloUPS.getTemperaturaUPS());
/*  421 */     setPercentualBateria(this.protocoloUPS.getPercentualBateria());
/*  422 */     setAutonomiaBateria(this.protocoloUPS.getTensaoBateria());
/*  423 */     setCarregandoBateria(this.protocoloUPS.isCarregandoBateria());
/*  424 */     setUsandoSomenteBateria(this.protocoloUPS.isUsandoSomenteBateria());
/*  425 */     setModoBypass(this.protocoloUPS.isModoBypass());
/*  426 */     setSuperAquecimento(this.protocoloUPS.isSuperAquecimento());
/*  427 */     setTemperaturaElevada(this.protocoloUPS.isTemperaturaElevada());
/*  428 */     setBateriaBaixa(this.protocoloUPS.isBateriaBaixa());
/*  429 */     setBateriaCritica(this.protocoloUPS.isBateriaCritica());
/*  430 */     setCargaElevada(this.protocoloUPS.isCargaElevada());
/*  431 */     setBateriaDescarregada(this.protocoloUPS.isBateriaDescarregada());
/*  432 */     setBateriaCarregada(this.protocoloUPS.isBateriaCarregada());
/*  433 */     setSobrecarga(this.protocoloUPS.isSobrecarga());
/*  434 */     setSegundos(this.protocoloUPS.getSegundos());
/*  435 */     setMinutos(this.protocoloUPS.getMinutos());
/*  436 */     setHora(this.protocoloUPS.getHora());
/*  437 */     setDiaSemana(this.protocoloUPS.getDiaSemana());
/*  438 */     setDiaMes(this.protocoloUPS.getDiaMes());
/*  439 */     setMes(this.protocoloUPS.getMes());
/*  440 */     setAno(this.protocoloUPS.getAno());
/*  441 */     setDiasSemanaProgramados(this.protocoloUPS.getDiasSemanaProgramados());
/*  442 */     setMinutoLigar(this.protocoloUPS.getMinutoLigar());
/*  443 */     setHoraLigar(this.protocoloUPS.getHoraLigar());
/*  444 */     setMinutoDesligar(this.protocoloUPS.getMinutoDesligar());
/*  445 */     setHoraDesligar(this.protocoloUPS.getHoraDesligar());
/*  446 */     setSaidaLigada(this.protocoloUPS.isSaidaLigada());
/*  447 */     setEntradaLigada(this.protocoloUPS.isEntradaLigada());
/*  448 */     setBypassAtivado(this.protocoloUPS.isBypassAtivado());
/*  449 */     downloadEventos();
/*      */     
/*  451 */     this.ouvinteUPS.notificaControle();
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
/*      */   public boolean setModoContinuo()
/*      */   {
/*  465 */     ProtocoloSolis protocoloSolis = (ProtocoloSolis)this.protocoloUPS;
/*  466 */     return protocoloSolis.setModoContinuo();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean setModoEvento()
/*      */   {
/*  476 */     ProtocoloSolis protocoloSolis = (ProtocoloSolis)this.protocoloUPS;
/*  477 */     return protocoloSolis.setModoEvento();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAno(int ano)
/*      */   {
/*  485 */     this.ano = ano;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutonomiaBateria(int tensaoBateriaBruta)
/*      */   {
/*  494 */     if (this.potenciaReal <= 20.0F) {
/*  495 */       this.autonomiaBateria = 170;
/*  496 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*      */     {
/*      */       try {
/*  499 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/*  502 */       if (tensaoBateriaBruta > 176) {
/*  503 */         this.autonomiaBateria = 75;
/*      */       }
/*  505 */       if (tensaoBateriaBruta < 139) {
/*  506 */         this.autonomiaBateria = 0;
/*      */       }
/*  508 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*      */     {
/*      */       try {
/*  511 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/*  514 */       if (tensaoBateriaBruta > 174) {
/*  515 */         this.autonomiaBateria = 34;
/*      */       }
/*  517 */       if (tensaoBateriaBruta < 139) {
/*  518 */         this.autonomiaBateria = 0;
/*      */       }
/*  520 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*      */     {
/*      */       try {
/*  523 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/*  526 */       if (tensaoBateriaBruta > 172) {
/*  527 */         this.autonomiaBateria = 23;
/*      */       }
/*  529 */       if (tensaoBateriaBruta < 142) {
/*  530 */         this.autonomiaBateria = 0;
/*      */       }
/*  532 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*      */     {
/*      */       try {
/*  535 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/*  538 */       if (tensaoBateriaBruta > 172) {
/*  539 */         this.autonomiaBateria = 20;
/*      */       }
/*  541 */       if (tensaoBateriaBruta < 141) {
/*  542 */         this.autonomiaBateria = 0;
/*      */       }
/*  544 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*      */     {
/*      */       try {
/*  547 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/*  550 */       if (tensaoBateriaBruta > 170) {
/*  551 */         this.autonomiaBateria = 14;
/*      */       }
/*  553 */       if (tensaoBateriaBruta < 141) {
/*  554 */         this.autonomiaBateria = 0;
/*      */       }
/*  556 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*      */     {
/*      */       try {
/*  559 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/*  562 */       if (tensaoBateriaBruta > 170) {
/*  563 */         this.autonomiaBateria = 12;
/*      */       }
/*  565 */       if (tensaoBateriaBruta < 141) {
/*  566 */         this.autonomiaBateria = 0;
/*      */       }
/*  568 */     } else if (this.potenciaReal > 650.0F)
/*      */     {
/*      */       try {
/*  571 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 139)];
/*      */       }
/*      */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/*  574 */       if (tensaoBateriaBruta > 170) {
/*  575 */         this.autonomiaBateria = 10;
/*      */       }
/*  577 */       if (tensaoBateriaBruta < 141) {
/*  578 */         this.autonomiaBateria = 0;
/*      */       }
/*      */     }
/*      */     
/*  582 */     if (this.expansorBateria > 0) {
/*  583 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaBaixa(boolean bateriaBaixa)
/*      */   {
/*  595 */     if (this.autonomiaBateria < this.comparadorAutonomiaBateria) {
/*  596 */       this.contaAutonomiasBaixas += 1;
/*      */     } else {
/*  598 */       this.contaAutonomiasBaixas = 0;
/*      */     }
/*  600 */     if (this.autonomiaBateria >= this.comparadorAutonomiaBateria) {
/*  601 */       this.bateriaBaixa = false;
/*      */     }
/*  603 */     if (this.contaAutonomiasBaixas > this.comparadorContadorAutonomiaBaixa) {
/*  604 */       this.bateriaBaixa = true;
/*      */     } else {
/*  606 */       this.bateriaBaixa = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaCarregada(boolean bateriaCarregada)
/*      */   {
/*  618 */     if ((this.modoRede) && (!this.carregandoBateria)) {
/*  619 */       this.bateriaCarregada = true;
/*      */     } else {
/*  621 */       this.bateriaCarregada = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBateriaCritica(boolean bateriaCritica)
/*      */   {
/*  629 */     this.bateriaCritica = bateriaCritica;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUsandoSomenteBateria(boolean usandoSomenteBateria)
/*      */   {
/*  638 */     if ((!this.modoRede) && (this.modoBateria)) {
/*  639 */       this.usandoSomenteBateria = true;
/*      */     } else {
/*  641 */       this.usandoSomenteBateria = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaDescarregada(boolean bateriaDescarregada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBypassAtivado(boolean bypassAtivado) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCargaElevada(boolean cargaElevada)
/*      */   {
/*  665 */     this.cargaElevada = (this.potenciaAparente > 1.2D * this.potenciaNominalVA);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCarregandoBateria(boolean carregandoBateria)
/*      */   {
/*  675 */     if ((carregandoBateria) && (this.modoRede)) {
/*  676 */       this.carregandoBateria = true;
/*      */     } else {
/*  678 */       this.carregandoBateria = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCorrenteEntrada(int correnteEntrada)
/*      */   {
/*  689 */     if (!this.modoRede) {
/*  690 */       this.correnteEntrada = 0.0F;
/*      */     } else {
/*  692 */       this.correnteEntrada = 
/*      */       
/*  694 */         (this.correnteEntradaF1MR / this.tensaoBateria - this.potenciaAparente / this.correnteEntradaF2MR + this.correnteSaida * (this.tensaoSaida / this.tensaoEntrada));
/*      */     }
/*  696 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/*  697 */       this.correnteEntrada = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCorrenteSaida(int correnteSaida)
/*      */   {
/*  706 */     if (!this.modoRede)
/*      */     {
/*  708 */       if (this.tensaoSaida220) {
/*  709 */         this.correnteSaida = (correnteSaida * this.correnteSaida220F1MB + this.correnteSaida220F2MB);
/*      */       } else {
/*  711 */         this.correnteSaida = (correnteSaida * this.correnteSaida110F1MB + this.correnteSaida110F2MB);
/*      */       }
/*      */       
/*      */     }
/*  715 */     else if (this.tensaoSaida220) {
/*  716 */       this.correnteSaida = (correnteSaida * this.correnteSaida220F1MR + this.correnteSaida220F2MR);
/*      */     } else {
/*  718 */       this.correnteSaida = (correnteSaida * this.correnteSaida110F1MR + this.correnteSaida110F2MR);
/*      */     }
/*  720 */     if (this.potenciaAparente < this.potenciaAparenteOffset) {
/*  721 */       this.correnteSaida = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDiaMes(int diaMes)
/*      */   {
/*  730 */     this.diaMes = diaMes;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDiaSemana(int diaSemana)
/*      */   {
/*  736 */     this.diaSemana = diaSemana;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDiasSemanaProgramados(boolean[] diasSemanaProgramados)
/*      */   {
/*  743 */     this.diasSemanaProgramados = diasSemanaProgramados;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEntradaLigada(boolean entradaLigada)
/*      */   {
/*  751 */     if (this.tensaoEntrada > 0.0F) {
/*  752 */       this.entradaLigada = true;
/*      */     } else {
/*  754 */       this.entradaLigada = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setExpansorBateria(int expansorBateria)
/*      */   {
/*  762 */     this.expansorBateria = expansorBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFatorPotenciaCarga(int fatorPotenciaCarga)
/*      */   {
/*  771 */     if (this.potenciaAparente <= 0.0F) {
/*  772 */       this.fatorPotenciaCarga = 0.0F;
/*      */     } else {
/*      */       try
/*      */       {
/*  776 */         this.fatorPotenciaCarga = ((int)(this.potenciaReal / this.potenciaAparente * 100.0F));
/*      */         
/*  778 */         if (this.fatorPotenciaCarga > 100.0F) {
/*  779 */           this.fatorPotenciaCarga = 100.0F;
/*      */         }
/*  781 */         this.fatorPotenciaCarga /= 100.0F;
/*      */       }
/*      */       catch (Exception e) {
/*  784 */         this.fatorPotenciaCarga = 100.0F;
/*  785 */         this.fatorPotenciaCarga /= 100.0F;
/*      */       }
/*      */     }
/*  788 */     if (this.potenciaAparente < this.potenciaAparenteOffset) {
/*  789 */       fatorPotenciaCarga = 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFrequenciaEntrada(int frequenciaEntrada)
/*      */   {
/*      */     try
/*      */     {
/*  800 */       this.frequenciaEntrada = (this.frequenciaEntradaC / frequenciaEntrada);
/*      */     } catch (Exception e) {
/*  802 */       this.frequenciaEntrada = 0.0F;
/*      */     }
/*      */     
/*  805 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/*  806 */       this.frequenciaEntrada = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFrequenciaSaida(int frequenciaSaida)
/*      */   {
/*  817 */     if (!this.modoBateria) {
/*  818 */       this.frequenciaSaida = 0.0F;
/*      */     }
/*  820 */     if ((this.modoRede) && (this.modoBateria)) {
/*  821 */       this.frequenciaSaida = this.frequenciaEntrada;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setHora(int hora)
/*      */   {
/*  829 */     this.hora = hora;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHoraDesligar(int horaDesligar)
/*      */   {
/*  835 */     this.horaDesligar = horaDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHoraLigar(int horaLigar)
/*      */   {
/*  841 */     this.horaLigar = horaLigar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*      */   {
/*  850 */     if (this.tensaoEntrada > 180.0F) {
/*  851 */       this.limiteInferiorTensaoEntrada = 150.0F;
/*      */     } else {
/*  853 */       this.limiteInferiorTensaoEntrada = 75.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*      */   {
/*  864 */     if (this.tensaoSaida220) {
/*  865 */       this.limiteInferiorTensaoSaida = 190.0F;
/*      */     } else {
/*  867 */       this.limiteInferiorTensaoSaida = 100.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*      */   {
/*  878 */     if (this.tensaoEntrada > 180.0F) {
/*  879 */       this.limiteSuperiorTensaoEntrada = 300.0F;
/*      */     } else {
/*  881 */       this.limiteSuperiorTensaoEntrada = 150.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*      */   {
/*  892 */     if (this.tensaoSaida220) {
/*  893 */       this.limiteSuperiorTensaoSaida = 250.0F;
/*      */     } else {
/*  895 */       this.limiteSuperiorTensaoSaida = 140.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMes(int mes)
/*      */   {
/*  903 */     this.mes = mes;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutoDesligar(int minutoDesligar)
/*      */   {
/*  909 */     this.minutoDesligar = minutoDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutoLigar(int minutoLigar)
/*      */   {
/*  915 */     this.minutoLigar = minutoLigar;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setMinutos(int minutos)
/*      */   {
/*  921 */     this.minutos = minutos;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setModoBateria(boolean modoBateria)
/*      */   {
/*  927 */     this.modoBateria = modoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoBypass(boolean modoBypass)
/*      */   {
/*  934 */     this.modoBypass = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoRede(boolean modoRede)
/*      */   {
/*  941 */     this.modoRede = modoRede;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPercentualBateria(int percentualBateria)
/*      */   {
/*  950 */     if (!this.modoRede) {
/*  951 */       this.percentualBateria = 
/*  952 */         ((int)((this.tensaoBateria - this.bateriaMinimoMB) * (100.0F / (this.bateriaMaximoMB - this.bateriaMinimoMB))));
/*      */     } else {
/*  954 */       this.percentualBateria = 
/*  955 */         ((int)((this.tensaoBateria - this.bateriaMinimoMR) * (100.0F / (this.bateriaMaximoMR - this.bateriaMinimoMR))));
/*      */     }
/*  957 */     if (this.percentualBateria < 0) {
/*  958 */       this.percentualBateria = 0;
/*      */     }
/*  960 */     if (this.percentualBateria > 100)
/*      */     {
/*  962 */       if (this.percentualBateria > 200) {
/*  963 */         this.percentualBateria = 0;
/*      */       } else {
/*  965 */         this.percentualBateria = 100;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPotenciaAparente(int potenciaAparente)
/*      */   {
/*  976 */     int correnteSaidaBruta = this.protocoloUPS.getCorrenteSaida();
/*  977 */     int tensaoSaidaBruta = this.protocoloUPS.getTensaoSaida();
/*      */     
/*  979 */     if (!this.modoRede)
/*      */     {
/*  981 */       if (this.tensaoSaida220) {
/*  982 */         this.potenciaAparente = 
/*  983 */           (correnteSaidaBruta * tensaoSaidaBruta * this.potenciaAparente220F1MB + this.potenciaAparente220F2MB);
/*      */       } else {
/*  985 */         this.potenciaAparente = 
/*  986 */           (correnteSaidaBruta * tensaoSaidaBruta * this.potenciaAparente110F1MB + this.potenciaAparente110F2MB);
/*      */       }
/*      */       
/*      */     }
/*  990 */     else if (this.tensaoSaida220) {
/*  991 */       this.potenciaAparente = 
/*  992 */         (correnteSaidaBruta * tensaoSaidaBruta * this.potenciaAparente220F1MR + this.potenciaAparente220F2MR);
/*      */     } else {
/*  994 */       this.potenciaAparente = 
/*  995 */         (correnteSaidaBruta * tensaoSaidaBruta * this.potenciaAparente110F1MR + this.potenciaAparente110F2MR);
/*      */     }
/*  997 */     if (this.potenciaAparente < this.potenciaAparenteOffset) {
/*  998 */       this.potenciaAparente = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPotenciaReal(int potenciaReal)
/*      */   {
/* 1007 */     if (!this.modoRede)
/*      */     {
/* 1009 */       if (this.tensaoSaida220) {
/* 1010 */         this.potenciaReal = (potenciaReal * this.potenciaReal220F1MB + this.potenciaReal220F2MB);
/*      */       } else {
/* 1012 */         this.potenciaReal = (potenciaReal * this.potenciaReal110F1MB + this.potenciaReal110F2MB);
/*      */       }
/*      */       
/*      */     }
/* 1016 */     else if (this.tensaoSaida220) {
/* 1017 */       this.potenciaReal = (potenciaReal * this.potenciaReal220F1MR + this.potenciaReal220F2MR);
/*      */     } else {
/* 1019 */       this.potenciaReal = (potenciaReal * this.potenciaReal110F1MR + this.potenciaReal110F2MR);
/*      */     }
/* 1021 */     if (this.potenciaAparente < this.potenciaAparenteOffset) {
/* 1022 */       this.potenciaReal = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSaidaLigada(boolean saidaLigada)
/*      */   {
/* 1033 */     this.saidaLigada = this.modoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSegundos(int segundos)
/*      */   {
/* 1041 */     this.segundos = segundos;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSobrecarga(boolean sobrecarga)
/*      */   {
/* 1047 */     this.sobrecarga = sobrecarga;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSuperAquecimento(boolean superAquecimento)
/*      */   {
/* 1053 */     this.superAquecimento = superAquecimento;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTemperaturaElevada(boolean temperaturaElevada)
/*      */   {
/* 1062 */     this.temperaturaElevada = (this.temperaturaUPS > this.temperaturaCritica);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTemperaturaUPS(int temperaturaUPS)
/*      */   {
/* 1071 */     this.temperaturaUPS = temperaturaUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoBateria(int tensaoBateria)
/*      */   {
/* 1078 */     this.tensaoBateria = (tensaoBateria * this.tensaoBateriaF1 + this.tensaoBateriaF2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoBoost(int tensaoBoost) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada(int tensaoEntrada)
/*      */   {
/* 1094 */     if (tensaoEntrada >= this.comparadorTensaoEntrada) {
/* 1095 */       this.tensaoEntrada = (tensaoEntrada * this.tensaoEntradaF1194 + this.tensaoEntradaF2194);
/*      */     } else {
/* 1097 */       this.tensaoEntrada = (tensaoEntrada * this.tensaoEntradaF1193 + this.tensaoEntradaF2193);
/*      */     }
/* 1099 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/* 1100 */       this.tensaoEntrada = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoEntradaNominal(int tensaoEntradaNominal)
/*      */   {
/* 1112 */     if (this.modoRede) {
/* 1113 */       if (this.tensaoEntrada > 180.0F) {
/* 1114 */         this.tensaoEntradaNominal = 220.0F;
/*      */       } else {
/* 1116 */         this.tensaoEntradaNominal = 110.0F;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*      */   {
/* 1125 */     this.tensaoEntrada220 = tensaoEntrada220;
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
/*      */   public void setTensaoSaida(int tensaoSaida)
/*      */   {
/* 1140 */     if (!this.modoRede)
/*      */     {
/* 1142 */       if (this.tensaoSaida220) {
/* 1143 */         this.tensaoSaida = (tensaoSaida * this.tensaoSaida220F1MB + this.tensaoSaida220F2MB);
/*      */       } else {
/* 1145 */         this.tensaoSaida = (tensaoSaida * this.tensaoSaida110F1MB + this.tensaoSaida110F2MB);
/*      */       }
/*      */       
/*      */     }
/* 1149 */     else if (this.tensaoSaida220) {
/* 1150 */       this.tensaoSaida = (tensaoSaida * this.tensaoSaida220F1MR + this.tensaoSaida220F2MR);
/*      */     } else {
/* 1152 */       this.tensaoSaida = (tensaoSaida * this.tensaoSaida110F1MR + this.tensaoSaida110F2MR);
/*      */     }
/* 1154 */     if (!this.modoBateria) {
/* 1155 */       this.tensaoSaida = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoSaidaNominal(int tensaoSaidaNominal)
/*      */   {
/* 1166 */     if (this.tensaoSaida220) {
/* 1167 */       this.tensaoSaidaNominal = 220.0F;
/*      */     }
/*      */     else {
/* 1170 */       this.tensaoSaidaNominal = 110.0F;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoSaida220(boolean tensaoSaida220)
/*      */   {
/* 1178 */     this.tensaoSaida220 = tensaoSaida220;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFatorPotenciaEquipamento(int fatorPotenciaEquipamento)
/*      */   {
/* 1188 */     this.fatorPotenciaEquipamento = fatorPotenciaEquipamento;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTemperaturaCritica(int temperaturaCritica)
/*      */   {
/* 1195 */     this.temperaturaCritica = temperaturaCritica;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPotenciaNominalVA(int potenciaNominalVA)
/*      */   {
/* 1202 */     this.potenciaNominalVA = potenciaNominalVA;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPotenciaNominalW(int potenciaNominalW)
/*      */   {
/* 1209 */     this.potenciaNominalW = potenciaNominalW;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1216 */   public void setBateriaTensaoNominal(int bateriaTensaoNominal) { this.bateriaTensaoNominal = bateriaTensaoNominal; }
/*      */   
/* 1218 */   public void setBateriaMaximoMB(float bateriaMaximoMB) { this.bateriaMaximoMB = bateriaMaximoMB; }
/*      */   
/* 1220 */   public void setBateriaMaximoMR(float bateriaMaximoMR) { this.bateriaMaximoMR = bateriaMaximoMR; }
/*      */   
/* 1222 */   public void setBateriaMinimoMB(float bateriaMinimoMB) { this.bateriaMinimoMB = bateriaMinimoMB; }
/*      */   
/* 1224 */   public void setBateriaMinimoMR(float bateriaMinimoMR) { this.bateriaMinimoMR = bateriaMinimoMR; }
/*      */   
/* 1226 */   public void setCalcAut0100(int[] calcAut0100) { this.calcAut0100 = calcAut0100; }
/*      */   
/* 1228 */   public void setCalcAut0200(int[] calcAut0200) { this.calcAut0200 = calcAut0200; }
/*      */   
/* 1230 */   public void setCalcAut0300(int[] calcAut0300) { this.calcAut0300 = calcAut0300; }
/*      */   
/* 1232 */   public void setCalcAut0400(int[] calcAut0400) { this.calcAut0400 = calcAut0400; }
/*      */   
/* 1234 */   public void setCalcAut0500(int[] calcAut0500) { this.calcAut0500 = calcAut0500; }
/*      */   
/* 1236 */   public void setCalcAut0600(int[] calcAut0600) { this.calcAut0600 = calcAut0600; }
/*      */   
/* 1238 */   public void setCalcAut0700(int[] calcAut0700) { this.calcAut0700 = calcAut0700; }
/*      */   
/* 1240 */   public void setCorrenteEntradaF1MR(float correnteEntradaF1MR) { this.correnteEntradaF1MR = correnteEntradaF1MR; }
/*      */   
/* 1242 */   public void setCorrenteEntradaF2MR(float correnteEntradaF2MR) { this.correnteEntradaF2MR = correnteEntradaF2MR; }
/*      */   
/* 1244 */   public void setCorrenteSaida110F1MB(float correnteSaida110F1MB) { this.correnteSaida110F1MB = correnteSaida110F1MB; }
/*      */   
/* 1246 */   public void setCorrenteSaida110F1MR(float correnteSaida110F1MR) { this.correnteSaida110F1MR = correnteSaida110F1MR; }
/*      */   
/* 1248 */   public void setCorrenteSaida110F2MB(float correnteSaida110F2MB) { this.correnteSaida110F2MB = correnteSaida110F2MB; }
/*      */   
/* 1250 */   public void setCorrenteSaida110F2MR(float correnteSaida110F2MR) { this.correnteSaida110F2MR = correnteSaida110F2MR; }
/*      */   
/* 1252 */   public void setCorrenteSaida220F1MB(float correnteSaida220F1MB) { this.correnteSaida220F1MB = correnteSaida220F1MB; }
/*      */   
/* 1254 */   public void setCorrenteSaida220F1MR(float correnteSaida220F1MR) { this.correnteSaida220F1MR = correnteSaida220F1MR; }
/*      */   
/* 1256 */   public void setCorrenteSaida220F2MB(float correnteSaida220F2MB) { this.correnteSaida220F2MB = correnteSaida220F2MB; }
/*      */   
/* 1258 */   public void setCorrenteSaida220F2MR(float correnteSaida220F2MR) { this.correnteSaida220F2MR = correnteSaida220F2MR; }
/*      */   
/* 1260 */   public void setFrequenciaEntrada(float frequenciaEntrada) { this.frequenciaEntrada = frequenciaEntrada; }
/*      */   
/* 1262 */   public void setPotenciaAparente110F1MB(float potenciaAparente110F1MB) { this.potenciaAparente110F1MB = potenciaAparente110F1MB; }
/*      */   
/* 1264 */   public void setPotenciaAparente110F1MR(float potenciaAparente110F1MR) { this.potenciaAparente110F1MR = potenciaAparente110F1MR; }
/*      */   
/* 1266 */   public void setPotenciaAparente110F2MB(float potenciaAparente110F2MB) { this.potenciaAparente110F2MB = potenciaAparente110F2MB; }
/*      */   
/* 1268 */   public void setPotenciaAparente110F2MR(float potenciaAparente110F2MR) { this.potenciaAparente110F2MR = potenciaAparente110F2MR; }
/*      */   
/* 1270 */   public void setPotenciaAparente220F1MB(float potenciaAparente220F1MB) { this.potenciaAparente220F1MB = potenciaAparente220F1MB; }
/*      */   
/* 1272 */   public void setPotenciaAparente220F1MR(float potenciaAparente220F1MR) { this.potenciaAparente220F1MR = potenciaAparente220F1MR; }
/*      */   
/* 1274 */   public void setPotenciaAparente220F2MB(float potenciaAparente220F2MB) { this.potenciaAparente220F2MB = potenciaAparente220F2MB; }
/*      */   
/* 1276 */   public void setPotenciaAparente220F2MR(float potenciaAparente220F2MR) { this.potenciaAparente220F2MR = potenciaAparente220F2MR; }
/*      */   
/* 1278 */   public void setPotenciaAparenteOffset(float potenciaAparenteOffset) { this.potenciaAparenteOffset = potenciaAparenteOffset; }
/*      */   
/* 1280 */   public void setPotenciaReal110F1MB(float potenciaReal110F1MB) { this.potenciaReal110F1MB = potenciaReal110F1MB; }
/*      */   
/* 1282 */   public void setPotenciaReal110F1MR(float potenciaReal110F1MR) { this.potenciaReal110F1MR = potenciaReal110F1MR; }
/*      */   
/* 1284 */   public void setPotenciaReal110F2MB(float potenciaReal110F2MB) { this.potenciaReal110F2MB = potenciaReal110F2MB; }
/*      */   
/* 1286 */   public void setPotenciaReal110F2MR(float potenciaReal110F2MR) { this.potenciaReal110F2MR = potenciaReal110F2MR; }
/*      */   
/* 1288 */   public void setPotenciaReal220F1MB(float potenciaReal220F1MB) { this.potenciaReal220F1MB = potenciaReal220F1MB; }
/*      */   
/* 1290 */   public void setPotenciaReal220F1MR(float potenciaReal220F1MR) { this.potenciaReal220F1MR = potenciaReal220F1MR; }
/*      */   
/* 1292 */   public void setPotenciaReal220F2MB(float potenciaReal220F2MB) { this.potenciaReal220F2MB = potenciaReal220F2MB; }
/*      */   
/* 1294 */   public void setPotenciaReal220F2MR(float potenciaReal220F2MR) { this.potenciaReal220F2MR = potenciaReal220F2MR; }
/*      */   
/* 1296 */   public void setTensaoBateriaF1(float tensaoBateriaF1) { this.tensaoBateriaF1 = tensaoBateriaF1; }
/*      */   
/* 1298 */   public void setTensaoBateriaF2(float tensaoBateriaF2) { this.tensaoBateriaF2 = tensaoBateriaF2; }
/*      */   
/* 1300 */   public void setTensaoEntradaF1193(float tensaoEntradaF1193) { this.tensaoEntradaF1193 = tensaoEntradaF1193; }
/*      */   
/* 1302 */   public void setTensaoEntradaF1194(float tensaoEntradaF1194) { this.tensaoEntradaF1194 = tensaoEntradaF1194; }
/*      */   
/* 1304 */   public void setTensaoEntradaF2193(float tensaoEntradaF2193) { this.tensaoEntradaF2193 = tensaoEntradaF2193; }
/*      */   
/* 1306 */   public void setTensaoEntradaF2194(float tensaoEntradaF2194) { this.tensaoEntradaF2194 = tensaoEntradaF2194; }
/*      */   
/* 1308 */   public void setTensaoEntradaOffset(float tensaoEntradaOffset) { this.tensaoEntradaOffset = tensaoEntradaOffset; }
/*      */   
/* 1310 */   public void setTensaoSaida110F1MB(float tensaoSaida110F1MB) { this.tensaoSaida110F1MB = tensaoSaida110F1MB; }
/*      */   
/* 1312 */   public void setTensaoSaida110F1MR(float tensaoSaida110F1MR) { this.tensaoSaida110F1MR = tensaoSaida110F1MR; }
/*      */   
/* 1314 */   public void setTensaoSaida110F2MB(float tensaoSaida110F2MB) { this.tensaoSaida110F2MB = tensaoSaida110F2MB; }
/*      */   
/* 1316 */   public void setTensaoSaida110F2MR(float tensaoSaida110F2MR) { this.tensaoSaida110F2MR = tensaoSaida110F2MR; }
/*      */   
/* 1318 */   public void setTensaoSaida220F1MB(float tensaoSaida220F1MB) { this.tensaoSaida220F1MB = tensaoSaida220F1MB; }
/*      */   
/* 1320 */   public void setTensaoSaida220F1MR(float tensaoSaida220F1MR) { this.tensaoSaida220F1MR = tensaoSaida220F1MR; }
/*      */   
/* 1322 */   public void setTensaoSaida220F2MB(float tensaoSaida220F2MB) { this.tensaoSaida220F2MB = tensaoSaida220F2MB; }
/*      */   
/* 1324 */   public void setTensaoSaida220F2MR(float tensaoSaida220F2MR) { this.tensaoSaida220F2MR = tensaoSaida220F2MR; }
/*      */   
/* 1326 */   public void setComparadorTensaoEntrada(int comparadorTensaoEntrada) { this.comparadorTensaoEntrada = comparadorTensaoEntrada; }
/*      */   
/* 1328 */   public void setComparadorAutonomiaBateria(int comparadorAutonomiaBateria) { this.comparadorAutonomiaBateria = comparadorAutonomiaBateria; }
/*      */   
/* 1330 */   public void setComparadorContadorAutonomiaBaixa(int comparadorContadorAutonomiaBaixa) { this.comparadorContadorAutonomiaBaixa = comparadorContadorAutonomiaBaixa; }
/*      */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisM11.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */