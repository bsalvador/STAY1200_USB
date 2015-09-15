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
/*     */ public class PS800
/*     */   extends AbstractUPS
/*     */ {
/*     */   protected ProtocoloPS protocoloUPS;
/*     */   protected int estadoReles;
/*  23 */   protected float TENSAO_ENTRADA_F1 = 1.64F;
/*  24 */   protected float TENSAO_ENTRADA_F2 = 3.34F;
/*  25 */   protected double TENSAO_BATERIA_F1 = 0.0711D;
/*  26 */   protected double TENSAO_BATERIA_F2 = 0.2525D;
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
/*  56 */   protected double[] TENSAO_SAIDA_F1_MR = { 0.7058159232072275D, 0.8353521009105338D, 0.8900756564307967D, 0.0D, 0.6681365671143182D, 0.7438262421898244D, 0.0D, 0.8654262224145391D };
/*  57 */   protected double[] TENSAO_SAIDA_F2_MR = { 12.141D, 5.2752D, 10.43D, 0.0D, 17.565D, 17.593D, 0.0D, 13.677D };
/*     */   
/*  59 */   protected double[] TENSAO_SAIDA_F1_MI = { 15.22D, 16.51D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  60 */   protected double[] TENSAO_SAIDA_F2_MI = { 10.82D, 12.29D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */   
/*  62 */   protected int[] autonomia100 = { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PS800(ProtocoloPS protocol)
/*     */   {
/*  72 */     setProtocoloUPS(protocol);
/*  73 */     protocol.setModeloUPS(9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setProtocoloUPS(ProtocoloPS protocolo)
/*     */   {
/*  80 */     this.protocoloUPS = protocolo;
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
/*  91 */     return false;
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
/* 107 */     return this.protocoloUPS.configuraCalendario(diaSemana, diaMes, mes, ano);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 113 */     return this.protocoloUPS.configuraCalendarioRelogio(diaSemana, diaMes, mes, ano, segundo, minuto, hora);
/*     */   }
/*     */   
/*     */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*     */   {
/* 118 */     return this.protocoloUPS.configuraRelogio(segundos, minutos, horas);
/*     */   }
/*     */   
/*     */   public boolean desativaBypass()
/*     */   {
/* 123 */     return false;
/*     */   }
/*     */   
/*     */   public boolean desligaEntrada()
/*     */   {
/* 128 */     return this.protocoloUPS.desligaEntrada();
/*     */   }
/*     */   
/*     */   public boolean desligaSaida()
/*     */   {
/* 133 */     return this.protocoloUPS.desligaSaida();
/*     */   }
/*     */   
/*     */   public void downloadEventos()
/*     */   {
/* 138 */     this.eventos = this.protocoloUPS.downloadEventos();
/*     */   }
/*     */   
/*     */   public boolean finalizarAutoteste()
/*     */   {
/* 143 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean iniciarAutoteste()
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ligaEntrada()
/*     */   {
/* 157 */     return this.protocoloUPS.ligaEntrada();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ligaSaida()
/*     */   {
/* 164 */     return this.protocoloUPS.ligaSaida();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programa(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar, int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 174 */     return this.protocoloUPS.configuraCalendarioRelogioSemana(diaSemana, diaMes, mes, ano, segundo, minuto, hora, dias, horaLigar, minutoLigar, 
/* 175 */       horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*     */   {
/* 183 */     return this.protocoloUPS.programaSemana(dias, horaLigar, minutoLigar, horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAno(int ano)
/*     */   {
/* 189 */     this.ano = ano;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int autonomiaBateria)
/*     */   {
/* 199 */     if (this.potenciaReal < 100.0F)
/*     */     {
/* 201 */       int i = autonomiaBateria - 151;
/* 202 */       if (i < 1) i = 1;
/* 203 */       if (i > this.autonomia100.length) { i = this.autonomia100.length;
/*     */       }
/* 205 */       this.autonomiaBateria = this.autonomia100[(this.autonomia100.length - i)];
/*     */ 
/*     */ 
/*     */     }
/* 209 */     else if (this.potenciaReal < 180.0F)
/*     */     {
/* 211 */       if (this.tensaoBateria >= 24.1F) {
/* 212 */         this.autonomiaBateria = 15;
/*     */       }
/* 214 */       else if (this.tensaoBateria >= 22.09F) {
/* 215 */         this.autonomiaBateria = 14;
/*     */       }
/* 217 */       else if (this.tensaoBateria >= 24.07F) {
/* 218 */         this.autonomiaBateria = 13;
/*     */       }
/* 220 */       else if (this.tensaoBateria >= 24.04F) {
/* 221 */         this.autonomiaBateria = 12;
/*     */       }
/* 223 */       else if (this.tensaoBateria >= 22.96F) {
/* 224 */         this.autonomiaBateria = 11;
/*     */ 
/*     */       }
/* 227 */       else if (this.tensaoBateria >= 22.91F) {
/* 228 */         this.autonomiaBateria = 10;
/*     */       }
/* 230 */       else if (this.tensaoBateria >= 22.85F) {
/* 231 */         this.autonomiaBateria = 9;
/*     */       }
/* 233 */       else if (this.tensaoBateria >= 22.78F) {
/* 234 */         this.autonomiaBateria = 8;
/*     */       }
/* 236 */       else if (this.tensaoBateria >= 22.7F) {
/* 237 */         this.autonomiaBateria = 7;
/*     */       }
/* 239 */       else if (this.tensaoBateria >= 22.61F) {
/* 240 */         this.autonomiaBateria = 6;
/*     */       }
/* 242 */       else if (this.tensaoBateria >= 22.52F) {
/* 243 */         this.autonomiaBateria = 5;
/*     */       }
/* 245 */       else if (this.tensaoBateria >= 22.4F) {
/* 246 */         this.autonomiaBateria = 4;
/*     */       }
/* 248 */       else if (this.tensaoBateria >= 22.27F) {
/* 249 */         this.autonomiaBateria = 3;
/*     */       }
/* 251 */       else if (this.tensaoBateria >= 22.0F) {
/* 252 */         this.autonomiaBateria = 2;
/*     */ 
/*     */       }
/* 255 */       else if (this.tensaoBateria >= 20.73F) {
/* 256 */         this.autonomiaBateria = 1;
/*     */       }
/*     */       
/*     */     }
/* 260 */     else if (this.potenciaReal < 200.0F)
/*     */     {
/* 262 */       if (this.tensaoBateria >= 22.94F) {
/* 263 */         this.autonomiaBateria = 10;
/*     */       }
/* 265 */       else if (this.tensaoBateria >= 22.9F) {
/* 266 */         this.autonomiaBateria = 7;
/*     */       }
/* 268 */       else if (this.tensaoBateria >= 22.79F) {
/* 269 */         this.autonomiaBateria = 6;
/*     */       }
/* 271 */       else if (this.tensaoBateria >= 22.69F) {
/* 272 */         this.autonomiaBateria = 5;
/*     */       }
/* 274 */       else if (this.tensaoBateria >= 22.52F) {
/* 275 */         this.autonomiaBateria = 4;
/*     */       }
/* 277 */       else if (this.tensaoBateria >= 22.39F) {
/* 278 */         this.autonomiaBateria = 3;
/*     */       }
/* 280 */       else if (this.tensaoBateria >= 22.17F) {
/* 281 */         this.autonomiaBateria = 2;
/*     */       }
/* 283 */       else if (this.tensaoBateria >= 20.87F) {
/* 284 */         this.autonomiaBateria = 1;
/*     */       }
/*     */       
/*     */     }
/* 288 */     else if (this.potenciaReal < 250.0F) {
/* 289 */       if (this.tensaoBateria >= 22.81F) {
/* 290 */         this.autonomiaBateria = 7;
/*     */       }
/* 292 */       else if (this.tensaoBateria >= 22.75F) {
/* 293 */         this.autonomiaBateria = 6;
/*     */       }
/* 295 */       else if (this.tensaoBateria >= 22.67F) {
/* 296 */         this.autonomiaBateria = 5;
/*     */       }
/* 298 */       else if (this.tensaoBateria >= 22.52F) {
/* 299 */         this.autonomiaBateria = 4;
/*     */       }
/* 301 */       else if (this.tensaoBateria >= 22.34F) {
/* 302 */         this.autonomiaBateria = 3;
/*     */       }
/* 304 */       else if (this.tensaoBateria >= 22.11F) {
/* 305 */         this.autonomiaBateria = 2;
/*     */       }
/* 307 */       else if (this.tensaoBateria >= 20.85F) {
/* 308 */         this.autonomiaBateria = 1;
/*     */       }
/*     */     }
/* 311 */     else if (this.potenciaReal < 300.0F)
/*     */     {
/* 313 */       if (autonomiaBateria > 154) {
/* 314 */         this.autonomiaBateria = 3;
/*     */       }
/* 316 */       else if (autonomiaBateria > 149) {
/* 317 */         this.autonomiaBateria = 2;
/*     */       } else {
/* 319 */         this.autonomiaBateria = 1;
/*     */       }
/*     */       
/*     */     }
/* 323 */     else if (autonomiaBateria > 143) {
/* 324 */       this.autonomiaBateria = 2;
/*     */     } else {
/* 326 */       this.autonomiaBateria = 1;
/*     */     }
/*     */     
/*     */ 
/* 330 */     if (this.expansorBateria != 0) {
/* 331 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 339 */     if (this.modoBateria) {
/* 340 */       this.bateriaBaixa = (this.tensaoBateria < 11.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaCarregada(boolean bateriaCarregada)
/*     */   {
/* 347 */     if (this.modoBateria) {
/* 348 */       this.bateriaCarregada = bateriaCarregada;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaCritica(boolean bateriaCritica)
/*     */   {
/* 355 */     this.bateriaCritica = (this.tensaoBateria < 10.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaDescarregada(boolean bateriaDescarregada)
/*     */   {
/* 361 */     this.bateriaDescarregada = bateriaDescarregada;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaTensaoNominal(int bateriaTensaoNominal)
/*     */   {
/* 367 */     this.bateriaTensaoNominal = 12.4F;
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
/* 381 */     this.cargaElevada = cargaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCarregandoBateria(boolean carregandoBateria)
/*     */   {
/* 388 */     this.carregandoBateria = carregandoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCorrenteEntrada(int correnteEntrada)
/*     */   {
/* 395 */     if (!this.modoRede) {
/* 396 */       this.correnteEntrada = 0.0F;
/*     */     } else {
/* 398 */       this.correnteEntrada = (1.1F * (this.potenciaAparente / this.tensaoEntrada));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 407 */     if (this.modoRede)
/*     */     {
/* 409 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */ 
/*     */     }
/* 413 */     else if (this.saidaLigada) {
/* 414 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 416 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDiaMes(int diaMes)
/*     */   {
/* 423 */     this.diaMes = diaMes;
/*     */   }
/*     */   
/*     */   public void setDiaSemana(int diaSemana) {
/* 427 */     this.diaSemana = diaSemana;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDiasSemanaProgramados(boolean[] diasSemanaProgramados)
/*     */   {
/* 436 */     this.diasSemanaProgramados = diasSemanaProgramados;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntradaLigada(boolean entradaLigada)
/*     */   {
/* 444 */     if (this.tensaoEntrada > 0.0F) {
/* 445 */       this.entradaLigada = true;
/*     */     } else {
/* 447 */       this.entradaLigada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setExpansorBateria(int expansorBateria)
/*     */   {
/* 455 */     this.expansorBateria = expansorBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setConfiguracaoReles(int conf)
/*     */   {
/* 465 */     this.estadoReles = ((conf & 0x38) >> 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFatorPotenciaCarga(int fatorPotenciaCarga)
/*     */   {
/* 473 */     if (this.potenciaAparente <= 0.0F) {
/* 474 */       this.fatorPotenciaCarga = 0.0F;
/*     */     } else {
/*     */       try
/*     */       {
/* 478 */         this.fatorPotenciaCarga = ((int)(this.potenciaReal / this.potenciaAparente * 100.0F));
/*     */         
/* 480 */         if (this.fatorPotenciaCarga > 100.0F) {
/* 481 */           this.fatorPotenciaCarga = 100.0F;
/*     */         }
/* 483 */         this.fatorPotenciaCarga /= 100.0F;
/*     */       }
/*     */       catch (Exception e) {
/* 486 */         this.fatorPotenciaCarga = 100.0F;
/* 487 */         this.fatorPotenciaCarga /= 100.0F;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isTemperaturaAvaliable()
/*     */   {
/* 495 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFatorPotenciaEquipamento(int fatorPotenciaEquipamento)
/*     */   {
/* 502 */     this.fatorPotenciaEquipamento = 60;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFrequenciaEntrada(int frequenciaEntrada)
/*     */   {
/* 509 */     if (this.tensaoEntrada > 0.0F) {
/* 510 */       this.frequenciaEntrada = ((float)(0.37D * (257 - (frequenciaEntrada >> 8))));
/*     */     } else {
/* 512 */       this.frequenciaEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFrequenciaSaida(int frequenciaSaida)
/*     */   {
/* 518 */     this.frequenciaSaida = 0.0F;
/*     */     
/* 520 */     if (!this.saidaLigada) {
/* 521 */       this.frequenciaSaida = 0.0F;
/*     */     }
/* 523 */     else if (this.modoRede) {
/* 524 */       this.frequenciaSaida = this.frequenciaEntrada;
/*     */     }
/* 526 */     else if (this.modoBateria) {
/* 527 */       this.frequenciaSaida = 60.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHora(int hora)
/*     */   {
/* 534 */     this.hora = hora;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraDesligar(int horaDesligar)
/*     */   {
/* 540 */     this.horaDesligar = horaDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraLigar(int horaLigar)
/*     */   {
/* 546 */     this.horaLigar = horaLigar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*     */   {
/* 552 */     if (this.tensaoEntrada220) {
/* 553 */       this.limiteInferiorTensaoEntrada = 130.0F;
/*     */     } else {
/* 555 */       this.limiteInferiorTensaoEntrada = 50.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*     */   {
/* 561 */     this.limiteInferiorTensaoSaida = 96.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*     */   {
/* 568 */     if (this.tensaoEntrada220) {
/* 569 */       this.limiteSuperiorTensaoEntrada = 280.0F;
/*     */     } else {
/* 571 */       this.limiteSuperiorTensaoEntrada = 180.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*     */   {
/* 579 */     this.limiteSuperiorTensaoSaida = 128.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMes(int mes)
/*     */   {
/* 586 */     this.mes = mes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutoDesligar(int minutoDesligar)
/*     */   {
/* 593 */     this.minutoDesligar = minutoDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutoLigar(int minutoLigar)
/*     */   {
/* 600 */     this.minutoLigar = minutoLigar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMinutos(int minutos)
/*     */   {
/* 607 */     this.minutos = minutos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModoBateria(boolean modoBateria)
/*     */   {
/* 614 */     this.modoBateria = modoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModoBypass(boolean modoBypass)
/*     */   {
/* 621 */     this.modoBypass = modoBypass;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModoRede(boolean modoRede)
/*     */   {
/* 629 */     this.modoRede = modoRede;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPercentualBateria(int percentualBateria)
/*     */   {
/* 638 */     if (this.carregandoBateria) {
/* 639 */       this.percentualBateria = ((int)(100.0F * ((this.tensaoBateria - this.TENSAO_MIN_BATERIA) / (this.TENSAO_MAX_BATERIA - this.TENSAO_MIN_BATERIA))));
/*     */     } else {
/* 641 */       this.percentualBateria = ((int)(100.0F * ((this.tensaoBateria - this.TENSAO_MIN_BATERIA) / (this.TENSAO_FLUT_BATERIA - this.TENSAO_MIN_BATERIA))));
/*     */     }
/* 643 */     if (this.percentualBateria > 100) this.percentualBateria = 100;
/* 644 */     if (this.percentualBateria < 0) { this.percentualBateria = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPotenciaAparente(int potenciaAparente)
/*     */   {
/* 652 */     this.potenciaAparente = (this.correnteSaida * this.tensaoSaida);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaNominalVA(int potenciaNominalVA)
/*     */   {
/* 661 */     this.potenciaNominalVA = 800;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPotenciaNominalW(int potenciaNominalW)
/*     */   {
/* 668 */     if (this.saidaLigada) {
/* 669 */       this.potenciaNominalW = ((int)(this.tensaoSaida * this.correnteSaida));
/*     */     } else {
/* 671 */       this.potenciaNominalW = 0;
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
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 684 */     double pLin = this.POTENCIAUTIL_DETECCAO_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA1_F2[this.estadoReles];
/* 685 */     double pVa = this.POTENCIAUTIL_DETECCAO_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA2_F2[this.estadoReles];
/* 686 */     double pVa2 = this.POTENCIAUTIL_DETECCAO_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA3_F2[this.estadoReles];
/*     */     
/* 688 */     double difLin = pLin - this.potenciaAparente;
/* 689 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 690 */     double difNlin = pVa - this.potenciaAparente;
/* 691 */     if (difLin < 0.0D) difLin *= -1.0D;
/* 692 */     double difNlin2 = pVa2 - this.potenciaAparente;
/* 693 */     if (difNlin2 < 0.0D) { difNlin2 *= -1.0D;
/*     */     }
/* 695 */     if ((difLin < difNlin) && (difLin < difNlin2)) {
/* 696 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA1_F2[this.estadoReles]));
/*     */     }
/* 698 */     else if (difNlin < difNlin2) {
/* 699 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA2_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA2_F2[this.estadoReles]));
/*     */     } else {
/* 701 */       this.potenciaReal = ((float)(this.POTENCIAUTIL_CURVA3_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_CURVA3_F2[this.estadoReles]));
/*     */     }
/* 703 */     if (!this.saidaLigada) {
/* 704 */       this.potenciaReal = 0.0F;
/*     */     }
/* 706 */     if (this.potenciaAparente < this.potenciaReal)
/*     */     {
/* 708 */       float f = this.potenciaAparente;
/* 709 */       this.potenciaAparente = this.potenciaReal;
/* 710 */       this.potenciaReal = f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSaidaLigada(boolean saidaLigada)
/*     */   {
/* 719 */     this.saidaLigada = saidaLigada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSegundos(int segundos)
/*     */   {
/* 726 */     this.segundos = segundos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/* 733 */     if (this.potenciaReal > 435.0F)
/*     */     {
/* 735 */       this.sobrecarga = true;
/* 736 */       this.cargaElevada = true;
/*     */     }
/*     */     else
/*     */     {
/* 740 */       this.sobrecarga = false;
/* 741 */       this.cargaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuperAquecimento(boolean superAquecimento)
/*     */   {
/* 748 */     this.superAquecimento = superAquecimento;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTemperaturaCritica(int temperaturaCritica)
/*     */   {
/* 754 */     this.temperaturaCritica = temperaturaCritica;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTemperaturaElevada(boolean temperaturaElevada)
/*     */   {
/* 761 */     this.temperaturaElevada = temperaturaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTemperaturaUPS(int temperaturaUPS)
/*     */   {
/* 768 */     this.temperaturaUPS = temperaturaUPS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 776 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoBoost(int tensaoBoost)
/*     */   {
/* 785 */     this.tensaoBoost = 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*     */   {
/* 793 */     this.tensaoEntrada220 = tensaoEntrada220;
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
/* 804 */     if (this.modoRede) {
/* 805 */       if (this.tensaoEntrada220) {
/* 806 */         this.tensaoEntradaNominal = 220.0F;
/*     */       } else {
/* 808 */         this.tensaoEntradaNominal = 115.0F;
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
/* 822 */     if (!this.saidaLigada)
/*     */     {
/* 824 */       this.tensaoSaida = 0.0F;
/*     */ 
/*     */     }
/* 827 */     else if (this.modoRede)
/*     */     {
/* 829 */       this.tensaoSaida = ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/*     */     }
/*     */     else
/*     */     {
/* 833 */       double a = tensaoSaida * 2;
/* 834 */       a /= 128.0D;
/*     */       
/* 836 */       this.tensaoSaida = ((float)(this.tensaoBateria * Math.sqrt(a) * this.TENSAO_SAIDA_F1_MI[this.estadoReles] - this.correnteSaida * this.TENSAO_SAIDA_F2_MI[this.estadoReles]));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida220(boolean tensaoSaida220) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTensaoSaidaNominal(int tensaoSaidaNominal)
/*     */   {
/* 849 */     if (this.tensaoSaida220) {
/* 850 */       this.tensaoSaidaNominal = 220.0F;
/*     */     }
/*     */     else {
/* 853 */       this.tensaoSaidaNominal = 115.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setUsandoSomenteBateria(boolean usandoSomenteBateria) {
/* 858 */     if ((this.saidaLigada) && (!this.modoRede)) {
/* 859 */       this.usandoSomenteBateria = true;
/*     */     } else {
/* 861 */       this.usandoSomenteBateria = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean shutdown() {
/* 866 */     return this.protocoloUPS.shutdown();
/*     */   }
/*     */   
/*     */   public boolean shutdownReligamento()
/*     */   {
/* 871 */     return false;
/*     */   }
/*     */   
/*     */   public void notifica()
/*     */   {
/* 876 */     setModoRede(this.protocoloUPS.getModoRede());
/* 877 */     setConfiguracaoReles(this.protocoloUPS.getConfiguracaoRele());
/* 878 */     setTensaoEntrada220(this.protocoloUPS.isTensaoEntrada220());
/* 879 */     setTensaoEntradaNominal(this.protocoloUPS.getTensaoEntradaNominal());
/* 880 */     setTensaoEntrada(this.protocoloUPS.getTensaoEntrada());
/* 881 */     setTensaoBateria(this.protocoloUPS.getTensaoBateria());
/* 882 */     setTensaoSaida220(this.protocoloUPS.isTensaoSaida220());
/* 883 */     setCorrenteSaida(this.protocoloUPS.getCorrenteSaida());
/* 884 */     setModoBateria(this.protocoloUPS.getModoBateria());
/* 885 */     setTensaoSaida(this.protocoloUPS.getTensaoSaida());
/* 886 */     setCorrenteEntrada(this.protocoloUPS.getCorrenteEntrada());
/* 887 */     setFrequenciaEntrada(this.protocoloUPS.getFrequenciaEntrada());
/* 888 */     setLimiteInferiorTensaoEntrada(this.protocoloUPS.getLimiteInferiorTensaoEntrada());
/* 889 */     setLimiteSuperiorTensaoEntrada(this.protocoloUPS.getLimiteSuperiorTensaoEntrada());
/* 890 */     setTensaoSaidaNominal(this.protocoloUPS.getTensaoSaidaNominal());
/* 891 */     setFrequenciaSaida(this.protocoloUPS.getFrequenciaSaida());
/* 892 */     setLimiteInferiorTensaoSaida(this.protocoloUPS.getLimiteInferiorTensaoSaida());
/* 893 */     setLimiteSuperiorTensaoSaida(this.protocoloUPS.getLimiteSuperiorTensaoSaida());
/* 894 */     setPotenciaAparente(this.protocoloUPS.getPotenciaAparente());
/* 895 */     setPotenciaReal(this.protocoloUPS.getPotenciaReal());
/* 896 */     setFatorPotenciaCarga(this.protocoloUPS.getFatorPotenciaCarga());
/* 897 */     setTensaoBoost(this.protocoloUPS.getTensaoBoost());
/* 898 */     setTemperaturaUPS(this.protocoloUPS.getTemperaturaUPS());
/* 899 */     setPercentualBateria(this.protocoloUPS.getPercentualBateria());
/* 900 */     setAutonomiaBateria(this.protocoloUPS.getTensaoBateria());
/* 901 */     setCarregandoBateria(this.protocoloUPS.isCarregandoBateria());
/* 902 */     setUsandoSomenteBateria(this.protocoloUPS.isUsandoSomenteBateria());
/* 903 */     setModoBypass(this.protocoloUPS.isModoBypass());
/* 904 */     setSuperAquecimento(this.protocoloUPS.isSuperAquecimento());
/* 905 */     setTemperaturaElevada(this.protocoloUPS.isTemperaturaElevada());
/* 906 */     setBateriaBaixa(this.protocoloUPS.isBateriaBaixa());
/* 907 */     setBateriaCritica(this.protocoloUPS.isBateriaCritica());
/* 908 */     setCargaElevada(this.protocoloUPS.isCargaElevada());
/* 909 */     setBateriaDescarregada(this.protocoloUPS.isBateriaDescarregada());
/* 910 */     setBateriaCarregada(this.protocoloUPS.isBateriaCarregada());
/* 911 */     setSobrecarga(this.protocoloUPS.isSobrecarga());
/* 912 */     setSegundos(this.protocoloUPS.getSegundos());
/* 913 */     setMinutos(this.protocoloUPS.getMinutos());
/* 914 */     setHora(this.protocoloUPS.getHora());
/* 915 */     setDiaSemana(this.protocoloUPS.getDiaSemana());
/* 916 */     setDiaMes(this.protocoloUPS.getDiaMes());
/* 917 */     setMes(this.protocoloUPS.getMes());
/* 918 */     setAno(this.protocoloUPS.getAno());
/* 919 */     setDiasSemanaProgramados(this.protocoloUPS.getDiasSemanaProgramados());
/* 920 */     setMinutoLigar(this.protocoloUPS.getMinutoLigar());
/* 921 */     setHoraLigar(this.protocoloUPS.getHoraLigar());
/* 922 */     setMinutoDesligar(this.protocoloUPS.getMinutoDesligar());
/* 923 */     setHoraDesligar(this.protocoloUPS.getHoraDesligar());
/* 924 */     setSaidaLigada(this.protocoloUPS.isSaidaLigada());
/* 925 */     setEntradaLigada(this.protocoloUPS.isEntradaLigada());
/* 926 */     setBypassAtivado(this.protocoloUPS.isBypassAtivado());
/* 927 */     downloadEventos();
/*     */     
/* 929 */     this.ouvinteUPS.notificaControle();
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
/* 943 */     this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 952 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 962 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 965 */     int mesAtual = calendar.get(2) + 1;
/* 966 */     int anoAtual = calendar.get(1);
/*     */     
/* 968 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/* 969 */     ProtocoloPS ps = this.protocoloUPS;
/* 970 */     ps.addEvento(evt);
/*     */     
/* 972 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\PS800.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */