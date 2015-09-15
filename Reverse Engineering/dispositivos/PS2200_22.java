/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloPS;
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
/*     */ 
/*     */ 
/*     */ public class PS2200_22
/*     */   extends PS800
/*     */ {
/*     */   double media;
/*  22 */   int contadort = 0;
/*     */   
/*  24 */   public float getTensaoSaidaNominal() { return 220.0F; }
/*     */   
/*     */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*     */   {
/*  28 */     this.limiteSuperiorTensaoSaida = 270.0F;
/*     */   }
/*     */   
/*     */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*     */   {
/*  33 */     this.limiteInferiorTensaoSaida = 170.0F;
/*     */   }
/*     */   
/*     */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*     */   {
/*  38 */     this.limiteSuperiorTensaoEntrada = 270.0F;
/*     */   }
/*     */   
/*     */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*     */   {
/*  43 */     this.limiteInferiorTensaoEntrada = 170.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected float TENSAO_ENTRADA_F1_115;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PS2200_22(ProtocoloPS protocol)
/*     */   {
/*  61 */     super(protocol);
/*  62 */     instanciasGlobais();
/*  63 */     protocol.setModeloUPS(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void instanciasGlobais()
/*     */   {
/*  70 */     this.TENSAO_ENTRADA_F1 = 1.5068F;
/*  71 */     this.TENSAO_ENTRADA_F2 = 11.37F;
/*     */     
/*  73 */     this.TENSAO_ENTRADA_F1_115 = 1.58F;
/*  74 */     this.TENSAO_ENTRADA_F2_115 = 2.86F;
/*     */     
/*  76 */     this.TENSAO_BATERIA_F1 = 0.16D;
/*  77 */     this.TENSAO_BATERIA_F2 = -0.76D;
/*     */     
/*  79 */     this.TENSAO_MIN_BATERIA = 20.0F;
/*  80 */     this.TENSAO_MAX_BATERIA = 29.5F;
/*  81 */     this.TENSAO_FLUT_BATERIA = 27.0F;
/*     */     
/*  83 */     this.CORRENTE_SAIDA_F1_MR = 0.06340000033378601D;
/*  84 */     this.CORRENTE_SAIDA_F2_MR = 0.4099000096321106D;
/*     */     
/*  86 */     this.CORRENTE_SAIDA_F1_MI = 0.06560000032186508D;
/*  87 */     this.CORRENTE_SAIDA_F2_MI = 0.38530001044273376D;
/*     */     
/*  89 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F1 = 
/*  90 */       new double[] { 0.2262D, 0.2262D, 0.2536D, 0.24D, 0.24D, 0.2303D, 0.2535D, 0.28D };
/*  91 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F1 = 
/*  92 */       new double[] { 0.0763D, 0.081D, 0.0919D, 0.0D, 0.0741D, 0.0828D, 0.0D, 0.0938D };
/*  93 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F1 = 
/*  94 */       new double[] { 0.0744D, 0.0808D, 0.0885D, 0.0D, 0.0732D, 0.084D, 0.0D, 0.0955D };
/*     */     
/*  96 */     this.POTENCIAUTIL_DETECCAO_CURVA1_F2 = 
/*  97 */       new double[] { 21.075D, 21.075D, 46.886D, 20.281D, 36.958D, 43.75D, 36.291D, 85.67D };
/*  98 */     this.POTENCIAUTIL_DETECCAO_CURVA2_F2 = new double[] { 81.732D, 94.459D, 
/*  99 */       86.686D, 0.0D, 84.657D, 84.999D, 0.0D, 78.097D };
/* 100 */     this.POTENCIAUTIL_DETECCAO_CURVA3_F2 = new double[] { 122.06D, 122.9D, 125.75D, 
/* 101 */       0.0D, 120.39D, 108.52D, 0.0D, 92.239D };
/*     */     
/* 103 */     this.POTENCIAUTIL_CURVA1_F1 = new double[] { 0.08040007075206226D, 0.0894D, 0.0999D, 0.0D, 
/* 104 */       0.0813D, 0.0905D, 0.0D, 0.1005D };
/* 105 */     this.POTENCIAUTIL_CURVA2_F1 = new double[] { 0.08630063689870031D, 0.0946D, 0.1068D, 0.0D, 
/* 106 */       0.086D, 0.0967D, 0.0D, 0.1088D };
/* 107 */     this.POTENCIAUTIL_CURVA3_F1 = new double[] { 0.0896001146881468D, 0.0991D, 0.1116D, 0.0D, 
/* 108 */       0.0967D, 0.1068D, 0.0D, 0.1169D };
/*     */     
/* 110 */     this.POTENCIAUTIL_CURVA1_F2 = new double[] { 45.292D, 41.928D, 41.727D, 0.0D, 
/* 111 */       40.269D, 41.81D, 0.0D, 43.458D };
/* 112 */     this.POTENCIAUTIL_CURVA2_F2 = new double[] { 8.3927D, 9.2393D, 8.2852D, 0.0D, 
/* 113 */       8.301D, 6.7636D, 0.0D, 8.2842D };
/* 114 */     this.POTENCIAUTIL_CURVA3_F2 = new double[] { -31.115D, -33.777D, -33.826D, 0.0D, 
/* 115 */       -59.513D, -57.729D, 0.0D, -41.333D };
/*     */     
/* 117 */     this.TENSAO_SAIDA_F1_MR = new double[] { 2.5085D, 2.8814D, 3.0D, 0.0D, 0.0D, 3.4571D, 3.2D, 
/* 118 */       0.0D };
/* 119 */     this.TENSAO_SAIDA_F2_MR = new double[] { 30.508D, 22.288D, 30.5D, 0.0D, 0.0D, -15.057D, 18.2D, 
/* 120 */       0.0D };
/*     */     
/* 122 */     this.TENSAO_SAIDA_F1_MI = new double[] { 2.5D, 2.5D, 2.5D, 2.5D, 2.5D, 2.5D, 2.5D, 2.5D };
/* 123 */     this.TENSAO_SAIDA_F2_MI = new double[] { 39.5D, 39.5D, 39.5D, 39.5D, 39.5D, 39.5D, 39.5D, 39.5D };
/*     */     
/* 125 */     this.autonomia100 = new int[] { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 
/* 126 */       13, 11, 9, 7, 4, 2, 1 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/* 134 */     if (this.potenciaReal > 1200.0F) {
/* 135 */       this.sobrecarga = true;
/* 136 */       this.cargaElevada = true;
/*     */     }
/*     */     else {
/* 139 */       this.sobrecarga = false;
/* 140 */       this.cargaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/*     */     double potRe;
/*     */     
/*     */ 
/*     */ 
/*     */     double potRe;
/*     */     
/*     */ 
/* 156 */     if (this.modoRede) {
/* 157 */       potRe = this.POTENCIAUTIL_DETECCAO_CURVA1_F1[this.estadoReles] * potenciaReal + this.POTENCIAUTIL_DETECCAO_CURVA1_F2[this.estadoReles];
/*     */     } else {
/* 159 */       potRe = 0.1994D * potenciaReal + 78.199D;
/*     */     }
/*     */     
/* 162 */     if ((!this.saidaLigada) || (this.potenciaAparente == 0.0F)) {
/* 163 */       potRe = 0.0D;
/*     */     }
/* 165 */     this.potenciaReal = ((float)potRe);
/*     */     
/* 167 */     if (this.potenciaReal > this.potenciaAparente) {
/* 168 */       this.potenciaAparente = this.potenciaReal;
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
/*     */ 
/*     */ 
/*     */   protected float TENSAO_ENTRADA_F2_115;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int tensaoBateria)
/*     */   {
/* 193 */     float autonomiaBateria = (float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2);
/*     */     
/* 195 */     if (!this.saidaLigada)
/*     */     {
/*     */ 
/* 198 */       this.autonomiaBateria = 0;
/* 199 */       return;
/*     */     }
/*     */     
/* 202 */     if (this.potenciaReal < 103.0F) {
/* 203 */       this.autonomiaBateria = Math.round(
/*     */       
/* 205 */         (float)(4.0561D * Math.pow(autonomiaBateria, 2.0D) - 175.36D * autonomiaBateria + 1899.4D));
/* 206 */       if (this.autonomiaBateria > 68) {
/* 207 */         this.autonomiaBateria = 68;
/*     */       }
/* 209 */     } else if (this.potenciaReal < 242.0F) {
/* 210 */       this.autonomiaBateria = Math.round(
/*     */       
/* 212 */         (float)(1.4616D * Math.pow(autonomiaBateria, 2.0D) - 60.009D * autonomiaBateria + 617.29D));
/* 213 */       if (this.autonomiaBateria > 32) {
/* 214 */         this.autonomiaBateria = 32;
/*     */       }
/* 216 */     } else if (this.potenciaReal < 350.0F) {
/* 217 */       this.autonomiaBateria = 
/* 218 */         Math.round((float)(-0.4561D * Math.pow(autonomiaBateria, 2.0D) + 
/* 219 */         25.521D * autonomiaBateria - 336.49D));
/* 220 */       if (this.autonomiaBateria > 16) {
/* 221 */         this.autonomiaBateria = 16;
/*     */       }
/* 223 */     } else if (this.potenciaReal < 470.0F) {
/* 224 */       this.autonomiaBateria = 
/* 225 */         Math.round((float)(0.4426D * Math.pow(autonomiaBateria, 2.0D) - 
/* 226 */         16.549D * autonomiaBateria + 152.03D));
/* 227 */       if (this.autonomiaBateria > 14) {
/* 228 */         this.autonomiaBateria = 14;
/*     */       }
/* 230 */     } else if (this.potenciaReal < 617.0F) {
/* 231 */       this.autonomiaBateria = 
/* 232 */         Math.round((float)(0.2526D * Math.pow(autonomiaBateria, 2.0D) - 
/* 233 */         8.4266D * autonomiaBateria + 66.345D));
/* 234 */       if (this.autonomiaBateria > 12) {
/* 235 */         this.autonomiaBateria = 12;
/*     */       }
/* 237 */     } else if (this.potenciaReal < 810.0F) {
/* 238 */       this.autonomiaBateria = Math.round(
/*     */       
/* 240 */         (float)(0.187D * Math.pow(this.tensaoBateria, 2.0D) - 6.4851D * this.tensaoBateria + 54.985D));
/* 241 */       if (this.autonomiaBateria > 9) {
/* 242 */         this.autonomiaBateria = 9;
/*     */       }
/* 244 */     } else if (this.potenciaReal < 1006.0F) {
/* 245 */       this.autonomiaBateria = 
/* 246 */         Math.round((float)(-0.0116D * Math.pow(autonomiaBateria, 2.0D) + 
/* 247 */         0.9699D * autonomiaBateria - 14.325D));
/* 248 */       if (this.autonomiaBateria > 4)
/* 249 */         this.autonomiaBateria = 4;
/* 250 */     } else if (this.potenciaReal < 1270.0F)
/*     */     {
/* 252 */       this.autonomiaBateria = 
/* 253 */         Math.round((float)(-0.048D * Math.pow(autonomiaBateria, 2.0D) + 
/* 254 */         3.0226D * autonomiaBateria - 40.889D));
/* 255 */       if (this.autonomiaBateria > 4)
/* 256 */         this.autonomiaBateria = 4;
/*     */     }
/* 258 */     if (this.expansorBateria != 0) {
/* 259 */       this.autonomiaBateria = ((int)(this.autonomiaBateria * (1.0D + this.expansorBateria / 14.0D)));
/*     */     }
/* 261 */     if (this.autonomiaBateria <= 0) {
/* 262 */       this.autonomiaBateria = 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 271 */     if (this.modoRede) {
/* 272 */       this.tensaoSaida = 
/* 273 */         ((float)(this.TENSAO_SAIDA_F1_MR[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MR[this.estadoReles]));
/*     */     }
/*     */     else
/*     */     {
/* 277 */       this.tensaoSaida = 
/* 278 */         ((float)(this.TENSAO_SAIDA_F1_MI[this.estadoReles] * tensaoSaida + this.TENSAO_SAIDA_F2_MI[this.estadoReles]));
/*     */     }
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
/*     */ 
/* 293 */     if (!this.saidaLigada) {
/* 294 */       this.tensaoSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 302 */     this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
/*     */   }
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
/* 314 */     this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
/*     */     
/* 316 */     if (this.tensaoEntrada < 50.0F) {
/* 317 */       this.tensaoEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 329 */     if (this.modoRede) {
/* 330 */       this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
/*     */ 
/*     */     }
/* 333 */     else if (this.saidaLigada) {
/* 334 */       this.correnteSaida = 
/* 335 */         ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
/*     */     } else {
/* 337 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */     
/* 340 */     if (correnteSaida == 0) {
/* 341 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 349 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 358 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 361 */     int mesAtual = calendar.get(2) + 1;
/* 362 */     int anoAtual = calendar.get(1);
/*     */     
/* 364 */     Evento evt = new Evento(29, calendar
/* 365 */       .get(11), calendar.get(12), 
/* 366 */       calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/*     */     
/* 368 */     this.protocoloUPS.addEvento(evt);
/*     */     
/* 370 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 378 */     this.bateriaBaixa = (this.tensaoBateria < 19.0F);
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\PS2200_22.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */