/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.eventos.UPSListener;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloPS;
/*     */ import br.com.schneider.sgm.ups.AbstractUPS;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Stay700_USB
/*     */   extends AbstractUPS
/*     */ {
/*     */   protected ProtocoloPS protocoloUPS;
/*     */   protected int estadoReles;
/*  23 */   protected float TENSAO_ENTRADA_F1 = 1.68F;
/*  24 */   protected float TENSAO_ENTRADA_F2 = 1.72F;
/*  25 */   protected double TENSAO_BATERIA_F1 = 0.07410000264644623D;
/*  26 */   protected double TENSAO_BATERIA_F2 = 0.6104999780654907D;
/*     */   
/*  28 */   protected float TENSAO_MIN_BATERIA = 10.0F;
/*  29 */   protected float TENSAO_MAX_BATERIA = 14.5F;
/*  30 */   protected float TENSAO_FLUT_BATERIA = 13.54F;
/*     */   
/*  32 */   protected double CORRENTE_SAIDA_F1_MR = 0.09580012262415696D;
/*  33 */   protected double CORRENTE_SAIDA_F2_MR = 0.44420753375977257D;
/*     */   
/*  35 */   protected double CORRENTE_SAIDA_F1_MI = 0.0968054211035818D;
/*  36 */   protected double CORRENTE_SAIDA_F2_MI = 0.4291845493562232D;
/*     */   
/*  38 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA1_F1 = { 0.079D, 0.089D, 0.0972D, 0.0D, 0.0805D, 0.0883D, 0.0D, 0.0981D };
/*  39 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA2_F1 = { 0.0763D, 0.081D, 0.0919D, 0.0D, 0.0741D, 0.0828D, 0.0D, 0.0938D };
/*  40 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA3_F1 = { 0.0744D, 0.0808D, 0.0885D, 0.0D, 0.0732D, 0.084D, 0.0D, 0.0955D };
/*     */   
/*  42 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA1_F2 = { 49.107D, 45.449D, 48.092D, 0.0D, 43.633D, 47.585D, 0.0D, 48.831D };
/*  43 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA2_F2 = { 81.732D, 94.459D, 86.686D, 0.0D, 84.657D, 84.999D, 0.0D, 78.097D };
/*  44 */   protected double[] POTENCIAUTIL_DETECCAO_CURVA3_F2 = { 122.06D, 122.9D, 125.75D, 0.0D, 120.39D, 108.52D, 0.0D, 92.239D };
/*     */   
/*  46 */   protected double[] POTENCIAUTIL_CURVA1_F1 = { 0.08040007075206226D, 0.0894D, 0.0999D, 0.0D, 0.0813D, 0.0905D, 0.0D, 0.1005D };
/*  47 */   protected double[] POTENCIAUTIL_CURVA2_F1 = { 0.08630063689870031D, 0.0946D, 0.1068D, 0.0D, 0.086D, 0.0967D, 0.0D, 0.1088D };
/*  48 */   protected double[] POTENCIAUTIL_CURVA3_F1 = { 0.0896001146881468D, 0.0991D, 0.1116D, 0.0D, 0.0967D, 0.1068D, 0.0D, 0.1169D };
/*     */   
/*  50 */   protected double[] POTENCIAUTIL_CURVA1_F2 = { 45.292D, 41.928D, 41.727D, 0.0D, 40.269D, 41.81D, 0.0D, 43.458D };
/*  51 */   protected double[] POTENCIAUTIL_CURVA2_F2 = { 8.3927D, 9.2393D, 8.2852D, 0.0D, 8.301D, 6.7636D, 0.0D, 8.2842D };
/*  52 */   protected double[] POTENCIAUTIL_CURVA3_F2 = { -31.115D, -33.777D, -33.826D, 0.0D, -59.513D, -57.729D, 0.0D, -41.333D };
/*     */   
/*     */ 
/*     */ 
/*  56 */   protected double[] TENSAO_SAIDA_F1_MR = { 0.7020000219345093D, 0.8406000137329102D, 0.0D, 0.0D, 0.0D, 0.8330000042915344D };
/*  57 */   protected double[] TENSAO_SAIDA_F2_MR = { 14.015000343322754D, 12.130000114440918D, 0.0D, 0.0D, 0.0D, 12.5D };
/*     */   
/*  59 */   protected double[] TENSAO_SAIDA_F1_MI = { 15.22D, 16.51D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  60 */   protected double[] TENSAO_SAIDA_F2_MI = { 10.82D, 12.29D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */   
/*  62 */   protected int[] autonomia100 = { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };
/*     */   
/*  64 */   private float[] FATOR_DESCARGA_055W = { 0.0339F, -8.75681F, 561.69F };
/*  65 */   private float[] FATOR_DESCARGA_130W = { 0.0126F, -2.981F, 173.12F };
/*  66 */   private float[] FATOR_DESCARGA_190W = { 0.0127F, -3.2775F, 210.72F };
/*  67 */   private float[] FATOR_DESCARGA_260W = { -0.0012F, 0.4685F, -41.17F };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stay700_USB(ProtocoloPS protocol)
/*     */   {
/*  77 */     setProtocoloUPS(protocol);
/*  78 */     protocol.setModeloUPS(9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setProtocoloUPS(ProtocoloPS protocolo)
/*     */   {
/*  85 */     this.protocoloUPS = protocolo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean ativaBypass()
/*     */   {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean configuraCalendario(int diaSemana, int diaMes, int mes, int ano)
/*     */   {
/* 112 */     return this.protocoloUPS.configuraCalendario(diaSemana, diaMes, mes, ano);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 118 */     return this.protocoloUPS.configuraCalendarioRelogio(diaSemana, diaMes, mes, ano, segundo, minuto, hora);
/*     */   }
/*     */   
/*     */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*     */   {
/* 123 */     return this.protocoloUPS.configuraRelogio(segundos, minutos, horas);
/*     */   }
/*     */   
/*     */   public boolean desativaBypass()
/*     */   {
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public boolean desligaEntrada()
/*     */   {
/* 133 */     return this.protocoloUPS.desligaEntrada();
/*     */   }
/*     */   
/*     */   public boolean desligaSaida()
/*     */   {
/* 138 */     return this.protocoloUPS.desligaSaida();
/*     */   }
/*     */   
/*     */   public void downloadEventos()
/*     */   {
/* 143 */     this.eventos = this.protocoloUPS.downloadEventos();
/*     */   }
/*     */   
/*     */   public boolean finalizarAutoteste()
/*     */   {
/* 148 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean iniciarAutoteste()
/*     */   {
/* 155 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ligaEntrada()
/*     */   {
/* 162 */     return this.protocoloUPS.ligaEntrada();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ligaSaida()
/*     */   {
/* 169 */     return this.protocoloUPS.ligaSaida();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programa(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar, int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 179 */     return this.protocoloUPS.configuraCalendarioRelogioSemana(diaSemana, diaMes, mes, ano, segundo, minuto, hora, dias, horaLigar, minutoLigar, 
/* 180 */       horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*     */   {
/* 188 */     return this.protocoloUPS.programaSemana(dias, horaLigar, minutoLigar, horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAno(int ano)
/*     */   {
/* 194 */     this.ano = ano;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int autonomiaBateria)
/*     */   {
/* 202 */     int autonomiaBateria55 = autonomiaBateria;
/* 203 */     int autonomiaBateria130 = autonomiaBateria;
/* 204 */     int autonomiaBateria190 = autonomiaBateria;
/* 205 */     int autonomiaBateria260 = autonomiaBateria;
/*     */     
/* 207 */     if (autonomiaBateria > 162) {
/* 208 */       autonomiaBateria55 = 162;
/*     */     }
/* 210 */     else if (autonomiaBateria < 136) {
/* 211 */       autonomiaBateria55 = 136;
/*     */     }
/* 213 */     if (autonomiaBateria > 152) {
/* 214 */       autonomiaBateria130 = 152;
/*     */     }
/* 216 */     else if (autonomiaBateria < 136) {
/* 217 */       autonomiaBateria130 = 136;
/*     */     }
/*     */     
/* 220 */     if (autonomiaBateria > 148) {
/* 221 */       autonomiaBateria190 = 148;
/*     */     }
/* 223 */     else if (autonomiaBateria < 132) {
/* 224 */       autonomiaBateria190 = 132;
/*     */     }
/* 226 */     if (autonomiaBateria > 146) {
/* 227 */       autonomiaBateria260 = 146;
/*     */     }
/* 229 */     else if (autonomiaBateria < 127) {
/* 230 */       autonomiaBateria260 = 127;
/*     */     }
/* 232 */     double fator55 = this.FATOR_DESCARGA_055W[0] * Math.pow(autonomiaBateria55, 2.0D) + this.FATOR_DESCARGA_055W[1] * autonomiaBateria55 + this.FATOR_DESCARGA_055W[2];
/* 233 */     double fator130 = this.FATOR_DESCARGA_130W[0] * Math.pow(autonomiaBateria130, 2.0D) + this.FATOR_DESCARGA_130W[1] * autonomiaBateria130 + this.FATOR_DESCARGA_130W[2];
/* 234 */     double fator190 = this.FATOR_DESCARGA_190W[0] * Math.pow(autonomiaBateria190, 2.0D) + this.FATOR_DESCARGA_190W[1] * autonomiaBateria190 + this.FATOR_DESCARGA_190W[2];
/* 235 */     double fator260 = this.FATOR_DESCARGA_260W[0] * Math.pow(autonomiaBateria260, 2.0D) + this.FATOR_DESCARGA_260W[1] * autonomiaBateria260 + this.FATOR_DESCARGA_260W[2];
/*     */     
/* 237 */     if (fator55 < 0.0D) {
/* 238 */       fator55 = 0.0D;
/*     */     }
/* 240 */     if (fator130 < 0.0D) {
/* 241 */       fator130 = 0.0D;
/*     */     }
/* 243 */     if (fator190 < 0.0D) {
/* 244 */       fator190 = 0.0D;
/*     */     }
/* 246 */     if (fator260 < 0.0D) {
/* 247 */       fator260 = 0.0D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 253 */     if (this.potenciaReal < 55.0F) {
/* 254 */       this.autonomiaBateria = 40;
/*     */     }
/* 256 */     else if (this.potenciaReal < 130.0F)
/*     */     {
/* 258 */       this.autonomiaBateria = 
/* 259 */         ((int)((this.potenciaReal - 55.0F) / 75.0F * fator130 + (130.0F - this.potenciaReal) / 75.0F * fator55));
/*     */ 
/*     */     }
/* 262 */     else if (this.potenciaReal < 190.0F)
/*     */     {
/* 264 */       this.autonomiaBateria = 
/* 265 */         ((int)((this.potenciaReal - 130.0F) / 60.0F * fator190 + (190.0F - this.potenciaReal) / 60.0F * fator130));
/*     */ 
/*     */     }
/* 268 */     else if (this.potenciaReal < 260.0F)
/*     */     {
/* 270 */       this.autonomiaBateria = 
/* 271 */         ((int)((this.potenciaReal - 190.0F) / 70.0F * fator260 + (260.0F - this.potenciaReal) / 70.0F * fator190));
/*     */     }
/*     */     else
/*     */     {
/* 275 */       this.autonomiaBateria = 1;
/*     */     }
/*     */     
/* 278 */     if (this.autonomiaBateria < 0) {
/* 279 */       this.autonomiaBateria = 0;
/*     */     }
/*     */     
/* 282 */     if (this.expansorBateria != 0) {
/* 283 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 291 */     if (this.modoBateria) {
/* 292 */       this.bateriaBaixa = (this.tensaoBateria < 11.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaCarregada(boolean bateriaCarregada)
/*     */   {
/* 299 */     if (this.modoBateria) {
/* 300 */       this.bateriaCarregada = bateriaCarregada;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaCritica(boolean bateriaCritica)
/*     */   {
/* 307 */     this.bateriaCritica = (this.tensaoBateria < 10.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaDescarregada(boolean bateriaDescarregada)
/*     */   {
/* 313 */     this.bateriaDescarregada = bateriaDescarregada;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaTensaoNominal(int bateriaTensaoNominal)
/*     */   {
/* 319 */     this.bateriaTensaoNominal = 12.4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBypassAtivado(boolean bypassAtivado) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCargaElevada(boolean cargaElevada)
/*     */   {
/* 333 */     this.cargaElevada = cargaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCarregandoBateria(boolean carregandoBateria)
/*     */   {
/* 340 */     this.carregandoBateria = carregandoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCorrenteEntrada(int correnteEntrada)
/*     */   {
/* 347 */     if (!this.modoRede) {
/* 348 */       this.correnteEntrada = 0.0F;
/*     */     } else {
/* 350 */       this.correnteEntrada = (1.1F * (this.potenciaAparente / this.tensaoEntrada));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 359 */     if (this.modoRede)
/*     */     {
/* 361 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */ 
/*     */     }
/* 365 */     else if (this.saidaLigada) {
/* 366 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 368 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDiaMes(int diaMes)
/*     */   {
/* 375 */     this.diaMes = diaMes;
/*     */   }
/*     */   
/*     */   public void setDiaSemana(int diaSemana) {
/* 379 */     this.diaSemana = diaSemana;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDiasSemanaProgramados(boolean[] diasSemanaProgramados)
/*     */   {
/* 388 */     this.diasSemanaProgramados = diasSemanaProgramados;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntradaLigada(boolean entradaLigada)
/*     */   {
/* 396 */     if (this.tensaoEntrada > 0.0F) {
/* 397 */       this.entradaLigada = true;
/*     */     } else {
/* 399 */       this.entradaLigada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setExpansorBateria(int expansorBateria)
/*     */   {
/* 407 */     this.expansorBateria = expansorBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setConfiguracaoReles(int conf)
/*     */   {
/* 417 */     this.estadoReles = ((conf & 0x38) >> 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFatorPotenciaCarga(int fatorPotenciaCarga)
/*     */   {
/* 426 */     if (this.potenciaAparente <= 0.0F) {
/* 427 */       this.fatorPotenciaCarga = 0.0F;
/*     */     } else {
/*     */       try
/*     */       {
/* 431 */         this.fatorPotenciaCarga = ((int)(this.potenciaReal / this.potenciaAparente * 100.0F));
/*     */         
/* 433 */         if (this.fatorPotenciaCarga > 100.0F) {
/* 434 */           this.fatorPotenciaCarga = 100.0F;
/*     */         }
/* 436 */         this.fatorPotenciaCarga /= 100.0F;
/*     */       }
/*     */       catch (Exception e) {
/* 439 */         this.fatorPotenciaCarga = 100.0F;
/* 440 */         this.fatorPotenciaCarga /= 100.0F;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isTemperaturaAvaliable()
/*     */   {
/* 448 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFatorPotenciaEquipamento(int fatorPotenciaEquipamento)
/*     */   {
/* 455 */     this.fatorPotenciaEquipamento = 42;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFrequenciaEntrada(int frequenciaEntrada)
/*     */   {
/* 462 */     if (this.tensaoEntrada > 30.0F) {
/* 463 */       this.frequenciaEntrada = ((float)(0.371D * (257 - (frequenciaEntrada >> 8))));
/*     */     } else {
/* 465 */       this.frequenciaEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFrequenciaSaida(int frequenciaSaida)
/*     */   {
/* 472 */     this.frequenciaSaida = 0.0F;
/*     */     
/* 474 */     if (!this.saidaLigada) {
/* 475 */       this.frequenciaSaida = 0.0F;
/*     */     }
/* 477 */     else if (this.modoRede) {
/* 478 */       this.frequenciaSaida = this.frequenciaEntrada;
/*     */     }
/* 480 */     else if (this.modoBateria) {
/* 481 */       this.frequenciaSaida = 60.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHora(int hora)
/*     */   {
/* 488 */     this.hora = hora;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraDesligar(int horaDesligar)
/*     */   {
/* 494 */     this.horaDesligar = horaDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraLigar(int horaLigar)
/*     */   {
/* 500 */     this.horaLigar = horaLigar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*     */   {
/* 506 */     if (this.tensaoEntrada220) {
/* 507 */       this.limiteInferiorTensaoEntrada = 130.0F;
/*     */     } else {
/* 509 */       this.limiteInferiorTensaoEntrada = 50.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*     */   {
/* 515 */     this.limiteInferiorTensaoSaida = 96.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*     */   {
/* 522 */     if (this.tensaoEntrada220) {
/* 523 */       this.limiteSuperiorTensaoEntrada = 280.0F;
/*     */     } else {
/* 525 */       this.limiteSuperiorTensaoEntrada = 180.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*     */   {
/* 533 */     this.limiteSuperiorTensaoSaida = 132.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMes(int mes)
/*     */   {
/* 540 */     this.mes = mes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutoDesligar(int minutoDesligar)
/*     */   {
/* 547 */     this.minutoDesligar = minutoDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutoLigar(int minutoLigar)
/*     */   {
/* 554 */     this.minutoLigar = minutoLigar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutos(int minutos)
/*     */   {
/* 561 */     this.minutos = minutos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModoBateria(boolean modoBateria)
/*     */   {
/* 568 */     this.modoBateria = modoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModoBypass(boolean modoBypass)
/*     */   {
/* 575 */     this.modoBypass = modoBypass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModoRede(boolean modoRede)
/*     */   {
/* 583 */     this.modoRede = modoRede;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPercentualBateria(int percentualBateria)
/*     */   {
/* 592 */     if (this.carregandoBateria) {
/* 593 */       this.percentualBateria = ((int)(100.0F * ((this.tensaoBateria - this.TENSAO_MIN_BATERIA) / (this.TENSAO_MAX_BATERIA - this.TENSAO_MIN_BATERIA))));
/*     */     } else {
/* 595 */       this.percentualBateria = ((int)(100.0F * ((this.tensaoBateria - this.TENSAO_MIN_BATERIA) / (this.TENSAO_FLUT_BATERIA - this.TENSAO_MIN_BATERIA))));
/*     */     }
/* 597 */     if (this.percentualBateria > 100) this.percentualBateria = 100;
/* 598 */     if (this.percentualBateria < 0) { this.percentualBateria = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPotenciaAparente(int potenciaAparente)
/*     */   {
/* 606 */     this.potenciaAparente = (this.correnteSaida * this.tensaoSaida);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaNominalVA(int potenciaNominalVA)
/*     */   {
/* 615 */     this.potenciaNominalVA = 700;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPotenciaNominalW(int potenciaNominalW)
/*     */   {
/* 622 */     if (this.saidaLigada) {
/* 623 */       this.potenciaNominalW = ((int)(this.tensaoSaida * this.correnteSaida));
/*     */     } else {
/* 625 */       this.potenciaNominalW = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 636 */     if (potenciaReal == 0) {
/* 637 */       this.potenciaAparente = 0.0F;
/* 638 */       this.potenciaReal = 0.0F;
/* 639 */       return;
/*     */     }
/*     */     
/* 642 */     double pLin = this.POTENCIAUTIL_DETECCAO_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA1_F2[this.estadoReles];
/* 643 */     double pVa = this.POTENCIAUTIL_DETECCAO_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA2_F2[this.estadoReles];
/* 644 */     double pVa2 = this.POTENCIAUTIL_DETECCAO_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA3_F2[this.estadoReles];
/*     */     
/* 646 */     double difLin = pLin - this.potenciaAparente;
/* 647 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 648 */     double difNlin = pVa - this.potenciaAparente;
/* 649 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 650 */     double difNlin2 = pVa2 - this.potenciaAparente;
/* 651 */     if (difNlin2 < 0.0D) { difNlin2 *= -1.0D;
/*     */     }
/* 653 */     if ((difLin < difNlin) && (difLin < difNlin2)) {
/* 654 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA1_F2[this.estadoReles]));
/*     */     }
/* 656 */     else if (difNlin < difNlin2) {
/* 657 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA2_F2[this.estadoReles]));
/*     */     } else {
/* 659 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA3_F2[this.estadoReles]));
/*     */     }
/* 661 */     if (!this.saidaLigada) {
/* 662 */       this.potenciaReal = 0.0F;
/*     */     }
/* 664 */     if (this.potenciaAparente < this.potenciaReal)
/*     */     {
/* 666 */       float f = this.potenciaAparente;
/* 667 */       this.potenciaAparente = this.potenciaReal;
/* 668 */       this.potenciaReal = f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSaidaLigada(boolean saidaLigada)
/*     */   {
/* 677 */     this.saidaLigada = saidaLigada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSegundos(int segundos)
/*     */   {
/* 684 */     this.segundos = segundos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/* 691 */     if (this.potenciaReal > 300.0F)
/*     */     {
/* 693 */       this.sobrecarga = true;
/* 694 */       this.cargaElevada = true;
/*     */     }
/*     */     else
/*     */     {
/* 698 */       this.sobrecarga = false;
/* 699 */       this.cargaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuperAquecimento(boolean superAquecimento)
/*     */   {
/* 706 */     this.superAquecimento = superAquecimento;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTemperaturaCritica(int temperaturaCritica)
/*     */   {
/* 712 */     this.temperaturaCritica = temperaturaCritica;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTemperaturaElevada(boolean temperaturaElevada)
/*     */   {
/* 719 */     this.temperaturaElevada = temperaturaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTemperaturaUPS(int temperaturaUPS)
/*     */   {
/* 726 */     this.temperaturaUPS = temperaturaUPS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 734 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBoost(int tensaoBoost)
/*     */   {
/* 743 */     this.tensaoBoost = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*     */   {
/* 751 */     this.tensaoEntrada220 = tensaoEntrada220;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntradaNominal(int tensaoEntradaNominal)
/*     */   {
/* 762 */     if (this.modoRede) {
/* 763 */       if (this.tensaoEntrada220) {
/* 764 */         this.tensaoEntradaNominal = 220.0F;
/*     */       } else {
/* 766 */         this.tensaoEntradaNominal = 115.0F;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 780 */     if (!this.saidaLigada)
/*     */     {
/* 782 */       this.tensaoSaida = 0.0F;
/*     */ 
/*     */     }
/* 785 */     else if (this.modoRede)
/*     */     {
/* 787 */       this.tensaoSaida = ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/*     */     }
/*     */     else
/*     */     {
/* 791 */       double a = tensaoSaida * 2;
/* 792 */       a /= 128.0D;
/*     */       
/* 794 */       this.tensaoSaida = ((float)(this.tensaoBateria * Math.sqrt(a) * this.TENSAO_SAIDA_F1_MI[this.estadoReles] - this.correnteSaida * this.TENSAO_SAIDA_F2_MI[this.estadoReles]));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida220(boolean tensaoSaida220) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTensaoSaidaNominal(int tensaoSaidaNominal)
/*     */   {
/* 806 */     if (this.tensaoSaida220) {
/* 807 */       this.tensaoSaidaNominal = 220.0F;
/*     */     }
/*     */     else {
/* 810 */       this.tensaoSaidaNominal = 115.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setUsandoSomenteBateria(boolean usandoSomenteBateria) {
/* 815 */     if ((this.saidaLigada) && (!this.modoRede)) {
/* 816 */       this.usandoSomenteBateria = true;
/*     */     } else {
/* 818 */       this.usandoSomenteBateria = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean shutdown() {
/* 823 */     return this.protocoloUPS.shutdown();
/*     */   }
/*     */   
/*     */   public boolean shutdownReligamento()
/*     */   {
/* 828 */     return false;
/*     */   }
/*     */   
/*     */   public void notifica()
/*     */   {
/* 833 */     setModoRede(this.protocoloUPS.getModoRede());
/* 834 */     setConfiguracaoReles(this.protocoloUPS.getConfiguracaoRele());
/* 835 */     setTensaoEntrada220(this.protocoloUPS.isTensaoEntrada220());
/* 836 */     setTensaoEntradaNominal(this.protocoloUPS.getTensaoEntradaNominal());
/* 837 */     setTensaoEntrada(this.protocoloUPS.getTensaoEntrada());
/* 838 */     setTensaoBateria(this.protocoloUPS.getTensaoBateria());
/* 839 */     setTensaoSaida220(this.protocoloUPS.isTensaoSaida220());
/* 840 */     setCorrenteSaida(this.protocoloUPS.getCorrenteSaida());
/* 841 */     setModoBateria(this.protocoloUPS.getModoBateria());
/* 842 */     setTensaoSaida(this.protocoloUPS.getTensaoSaida());
/* 843 */     setCorrenteEntrada(this.protocoloUPS.getCorrenteEntrada());
/* 844 */     setFrequenciaEntrada(this.protocoloUPS.getFrequenciaEntrada());
/* 845 */     setLimiteInferiorTensaoEntrada(this.protocoloUPS.getLimiteInferiorTensaoEntrada());
/* 846 */     setLimiteSuperiorTensaoEntrada(this.protocoloUPS.getLimiteSuperiorTensaoEntrada());
/* 847 */     setTensaoSaidaNominal(this.protocoloUPS.getTensaoSaidaNominal());
/* 848 */     setFrequenciaSaida(this.protocoloUPS.getFrequenciaSaida());
/* 849 */     setLimiteInferiorTensaoSaida(this.protocoloUPS.getLimiteInferiorTensaoSaida());
/* 850 */     setLimiteSuperiorTensaoSaida(this.protocoloUPS.getLimiteSuperiorTensaoSaida());
/* 851 */     setPotenciaAparente(this.protocoloUPS.getPotenciaAparente());
/* 852 */     setPotenciaReal(this.protocoloUPS.getPotenciaReal());
/* 853 */     setFatorPotenciaCarga(this.protocoloUPS.getFatorPotenciaCarga());
/* 854 */     setTensaoBoost(this.protocoloUPS.getTensaoBoost());
/* 855 */     setTemperaturaUPS(this.protocoloUPS.getTemperaturaUPS());
/* 856 */     setPercentualBateria(this.protocoloUPS.getPercentualBateria());
/* 857 */     setAutonomiaBateria(this.protocoloUPS.getTensaoBateria());
/* 858 */     setCarregandoBateria(this.protocoloUPS.isCarregandoBateria());
/* 859 */     setUsandoSomenteBateria(this.protocoloUPS.isUsandoSomenteBateria());
/* 860 */     setModoBypass(this.protocoloUPS.isModoBypass());
/* 861 */     setSuperAquecimento(this.protocoloUPS.isSuperAquecimento());
/* 862 */     setTemperaturaElevada(this.protocoloUPS.isTemperaturaElevada());
/* 863 */     setBateriaBaixa(this.protocoloUPS.isBateriaBaixa());
/* 864 */     setBateriaCritica(this.protocoloUPS.isBateriaCritica());
/* 865 */     setCargaElevada(this.protocoloUPS.isCargaElevada());
/* 866 */     setBateriaDescarregada(this.protocoloUPS.isBateriaDescarregada());
/* 867 */     setBateriaCarregada(this.protocoloUPS.isBateriaCarregada());
/* 868 */     setSobrecarga(this.protocoloUPS.isSobrecarga());
/* 869 */     setSegundos(this.protocoloUPS.getSegundos());
/* 870 */     setMinutos(this.protocoloUPS.getMinutos());
/* 871 */     setHora(this.protocoloUPS.getHora());
/* 872 */     setDiaSemana(this.protocoloUPS.getDiaSemana());
/* 873 */     setDiaMes(this.protocoloUPS.getDiaMes());
/* 874 */     setMes(this.protocoloUPS.getMes());
/* 875 */     setAno(this.protocoloUPS.getAno());
/* 876 */     setDiasSemanaProgramados(this.protocoloUPS.getDiasSemanaProgramados());
/* 877 */     setMinutoLigar(this.protocoloUPS.getMinutoLigar());
/* 878 */     setHoraLigar(this.protocoloUPS.getHoraLigar());
/* 879 */     setMinutoDesligar(this.protocoloUPS.getMinutoDesligar());
/* 880 */     setHoraDesligar(this.protocoloUPS.getHoraDesligar());
/* 881 */     setSaidaLigada(this.protocoloUPS.isSaidaLigada());
/* 882 */     setEntradaLigada(this.protocoloUPS.isEntradaLigada());
/* 883 */     setBypassAtivado(this.protocoloUPS.isBypassAtivado());
/* 884 */     downloadEventos();
/*     */     
/* 886 */     this.ouvinteUPS.notificaControle();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 900 */     this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 909 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 919 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 922 */     int mesAtual = calendar.get(2) + 1;
/* 923 */     int anoAtual = calendar.get(1);
/*     */     
/* 925 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/* 926 */     ProtocoloPS ps = this.protocoloUPS;
/* 927 */     ps.addEvento(evt);
/*     */     
/* 929 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\Stay700_USB.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */