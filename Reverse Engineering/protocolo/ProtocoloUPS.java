/*      */ package br.com.schneider.sgm.protocolo;
/*      */ 
/*      */ import br.com.schneider.sgm.comunicacao.Communication;
/*      */ import br.com.schneider.sgm.eventos.DriverListener;
/*      */ import br.com.schneider.sgm.eventos.Evento;
/*      */ import br.com.schneider.sgm.eventos.ProtocoloListener;
/*      */ import java.util.TimerTask;
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
/*      */ public abstract class ProtocoloUPS
/*      */   implements DriverListener
/*      */ {
/*      */   public static final int NUMERO_TIMEOUTS = 0;
/*      */   public int ano;
/*      */   public int autonomiaBateria;
/*      */   public boolean bateriaBaixa;
/*      */   public boolean bateriaCarregada;
/*      */   public boolean bateriaCritica;
/*      */   public boolean bateriaDescarregada;
/*      */   public boolean bypassAtivado;
/*      */   public boolean cargaElevada;
/*      */   public boolean carregandoBateria;
/*      */   public Communication comunicador;
/*      */   public int correnteEntrada;
/*      */   public int correnteSaida;
/*      */   public int diaMes;
/*      */   public int diaSemana;
/*  101 */   public boolean[] diasSemanaProgramados = new boolean[7];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean entradaLigada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean expansorBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int fatorPotenciaCarga;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int frequenciaEntrada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int frequenciaSaida;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hora;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int horaDesligar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int horaLigar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int limiteInferiorTensaoEntrada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int limiteInferiorTensaoSaida;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int limiteSuperiorTensaoEntrada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int limiteSuperiorTensaoSaida;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int mes;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int minutoDesligar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int minutoLigar;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int minutos;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean modoBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean modoBypass;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean usandoSomenteBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean modoRede;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int percentualBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int potenciaAparente;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int potenciaReal;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean saidaLigada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int segundos;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean sobrecarga;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean superAquecimento;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean temperaturaElevada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int temperaturaUPS;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int tensaoBateria;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int tensaoBoost;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int tensaoEntradaNominal;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int tensaoSaidaNominal;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int tensaoEntrada;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tensaoEntrada220;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int tensaoSaida;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tensaoSaida220;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean esperaACK;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int consumodoDia;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int cabecalhoPacote;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  328 */   public int comandoAceito = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int modeloUPS;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int numeroTimeout;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected ProtocoloListener ouvinteProtocolo;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean ligaEntrada();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean desligaEntrada();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean ligaSaida();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean desligaSaida();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean ativaBypass();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean desativaBypass();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean configuraRelogio(int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean configuraCalendario(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean programaSemana(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean configuraCalendarioRelogioSemana(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean[] paramArrayOfBoolean, int paramInt8, int paramInt9, int paramInt10, int paramInt11);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean configuraCalendarioRelogio(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean shutdown();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean shutdownReligamento();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean pedidoDumpping();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void pedidoDados();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract Evento[] downloadEventos();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean setModoEvento();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean setModoContinuo();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void trataPacoteDumpping(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected abstract void trataPacoteDados();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void recebeDados();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean enviaDados(int[] paramArrayOfInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addProtocoloListener(ProtocoloListener ouvinteProtocolo)
/*      */   {
/*  488 */     this.ouvinteProtocolo = ouvinteProtocolo;
/*      */   }
/*      */   
/*      */   class TimeoutEnvio extends TimerTask {
/*      */     TimeoutEnvio() {}
/*      */     
/*      */     public void run() {
/*  495 */       if (ProtocoloUPS.this.esperaACK) {
/*  496 */         ProtocoloUPS.this.numeroTimeout += 1;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  501 */         ProtocoloUPS.this.comandoAceito = 2;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  506 */         ProtocoloUPS.this.numeroTimeout = 0;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  511 */         ProtocoloUPS.this.comandoAceito = 1;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int getNUMERO_TIMEOUTS()
/*      */   {
/*  520 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getAno()
/*      */   {
/*  527 */     return this.ano;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getAutonomiaBateria()
/*      */   {
/*  534 */     return this.autonomiaBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBateriaBaixa()
/*      */   {
/*  541 */     return this.bateriaBaixa;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBateriaCarregada()
/*      */   {
/*  548 */     return this.bateriaCarregada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBateriaCritica()
/*      */   {
/*  555 */     return this.bateriaCritica;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isBateriaDescarregada()
/*      */   {
/*  562 */     return this.bateriaDescarregada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBypassAtivado()
/*      */   {
/*  570 */     return this.bypassAtivado;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isCargaElevada()
/*      */   {
/*  577 */     return this.cargaElevada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isCarregandoBateria()
/*      */   {
/*  584 */     return this.carregandoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getComandoAceito()
/*      */   {
/*  591 */     return this.comandoAceito;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Communication getComunicador()
/*      */   {
/*  598 */     return this.comunicador;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getCorrenteEntrada()
/*      */   {
/*  605 */     return this.correnteEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getCorrenteSaida()
/*      */   {
/*  612 */     return this.correnteSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getDiaMes()
/*      */   {
/*  619 */     return this.diaMes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getDiaSemana()
/*      */   {
/*  626 */     return this.diaSemana;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean[] getDiasSemanaProgramados()
/*      */   {
/*  633 */     return this.diasSemanaProgramados;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isEntradaLigada()
/*      */   {
/*  640 */     return this.entradaLigada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isEsperaACK()
/*      */   {
/*  647 */     return this.esperaACK;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isExpansorBateria()
/*      */   {
/*  654 */     return this.expansorBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFatorPotenciaCarga()
/*      */   {
/*  661 */     return this.fatorPotenciaCarga;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFrequenciaEntrada()
/*      */   {
/*  668 */     return this.frequenciaEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFrequenciaSaida()
/*      */   {
/*  675 */     return this.frequenciaSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getHora()
/*      */   {
/*  682 */     return this.hora;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getHoraDesligar()
/*      */   {
/*  689 */     return this.horaDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getHoraLigar()
/*      */   {
/*  696 */     return this.horaLigar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getLimiteInferiorTensaoEntrada()
/*      */   {
/*  703 */     return this.limiteInferiorTensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getLimiteInferiorTensaoSaida()
/*      */   {
/*  710 */     return this.limiteInferiorTensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getLimiteSuperiorTensaoEntrada()
/*      */   {
/*  717 */     return this.limiteSuperiorTensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getLimiteSuperiorTensaoSaida()
/*      */   {
/*  724 */     return this.limiteSuperiorTensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMes()
/*      */   {
/*  731 */     return this.mes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMinutoDesligar()
/*      */   {
/*  738 */     return this.minutoDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMinutoLigar()
/*      */   {
/*  745 */     return this.minutoLigar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMinutos()
/*      */   {
/*  752 */     return this.minutos;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getModeloUPS()
/*      */   {
/*  759 */     return this.modeloUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getModoRede()
/*      */   {
/*  766 */     return this.modoRede;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getModoBateria()
/*      */   {
/*  773 */     return this.modoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isModoBypass()
/*      */   {
/*  780 */     return this.modoBypass;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isModoRede()
/*      */   {
/*  787 */     return this.modoRede;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getNumeroTimeout()
/*      */   {
/*  796 */     return this.numeroTimeout;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPercentualBateria()
/*      */   {
/*  805 */     return this.percentualBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPotenciaAparente()
/*      */   {
/*  814 */     return this.potenciaAparente;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPotenciaReal()
/*      */   {
/*  823 */     return this.potenciaReal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSaidaLigada()
/*      */   {
/*  832 */     return this.saidaLigada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSegundos()
/*      */   {
/*  841 */     return this.segundos;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSobrecarga()
/*      */   {
/*  850 */     return this.sobrecarga;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSuperAquecimento()
/*      */   {
/*  859 */     return this.superAquecimento;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isTemperaturaElevada()
/*      */   {
/*  868 */     return this.temperaturaElevada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTemperaturaUPS()
/*      */   {
/*  877 */     return this.temperaturaUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTensaoBateria()
/*      */   {
/*  886 */     return this.tensaoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getTensaoBoost()
/*      */   {
/*  892 */     return this.tensaoBoost;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTensaoEntrada()
/*      */   {
/*  901 */     return this.tensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isTensaoEntrada220()
/*      */   {
/*  910 */     return this.tensaoEntrada220;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTensaoSaida()
/*      */   {
/*  919 */     return this.tensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isTensaoSaida220()
/*      */   {
/*  928 */     return this.tensaoSaida220;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isUsandoSomenteBateria()
/*      */   {
/*  935 */     return this.usandoSomenteBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setAno(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setAutonomiaBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setBateriaBaixa(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setBateriaCarregada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setBateriaCritica(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setBateriaDescarregada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setBypassAtivado(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setCargaElevada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setCarregandoBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setComandoAceito(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setComunicador(Communication comunicador)
/*      */   {
/* 1013 */     this.comunicador = comunicador;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setCorrenteEntrada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setCorrenteSaida(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setDiaMes(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setDiaSemana(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setDiasSemanaProgramados(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setEntradaLigada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setExpansorBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setFatorPotenciaCarga(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setFrequenciaEntrada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setFrequenciaSaida(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setHora(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setHoraDesligar(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setHoraLigar(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setLimiteInferiorTensaoEntrada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setLimiteInferiorTensaoSaida(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setLimiteSuperiorTensaoEntrada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setLimiteSuperiorTensaoSaida(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setMes(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setMinutoDesligar(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setMinutoLigar(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setMinutos(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setModeloUPS(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setModoBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setModoBypass(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setModoRede(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setNumeroTimeout(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setPercentualBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setPotenciaAparente(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setPotenciaReal(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setSaidaLigada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setSegundos(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setSobrecarga(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setSuperAquecimento(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTemperaturaElevada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTemperaturaUPS(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoBateria(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoBoost(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoEntrada(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoEntrada220(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoSaida(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setTensaoSaida220(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTensaoEntradaNominal()
/*      */   {
/* 1306 */     return this.tensaoEntradaNominal;
/*      */   }
/*      */   
/*      */   public abstract void setTensaoEntradaNominal(int paramInt);
/*      */   
/*      */   public int getTensaoSaidaNominal() {
/* 1312 */     return this.tensaoSaidaNominal;
/*      */   }
/*      */   
/*      */   public abstract void setTensaoSaidaNominal(int paramInt);
/*      */   
/* 1317 */   public int getCabecalhoPacote() { return this.cabecalhoPacote; }
/*      */   
/*      */   public abstract void setCabecalhoPacote(int paramInt);
/*      */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\protocolo\ProtocoloUPS.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */