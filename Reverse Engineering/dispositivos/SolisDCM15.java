/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloSolis;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SolisDCM15
/*     */   extends SolisM11
/*     */ {
/*     */   private int cntSobreTemperatura;
/*  17 */   protected int[] calcAut0100 = { 1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 8, 10, 11, 13, 15, 18, 22, 24, 28, 34, 40, 43, 48, 56, 65, 70, 76, 85, 93, 100, 110, 122, 132, 141, 152, 162 };
/*     */   
/*  19 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 22, 25, 30, 35, 40, 44, 48, 54, 60, 65, 73, 80, 85, 92, 94, 95 };
/*     */   
/*  21 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 8, 10, 14, 16, 18, 20, 24, 30, 32, 36, 40, 44, 48, 52, 54, 58, 59, 60, 62, 62, 62 };
/*     */   
/*  23 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 8, 9, 10, 12, 13, 15, 17, 20, 22, 24, 28, 32, 35, 38, 42 };
/*     */   
/*  25 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 11, 12, 14, 15, 17, 18, 21, 24, 26, 29, 32, 34, 36 };
/*     */   
/*  27 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 20, 22, 24, 26, 28 };
/*     */   
/*  29 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 13, 14, 15, 17, 19, 21, 22 };
/*     */   
/*  31 */   protected int[] calcAut0800 = { 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19, 20, 20 };
/*     */   
/*  33 */   protected int[] calcAut0900 = { 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 7, 7, 8, 9, 10, 11, 12, 13, 15, 15, 16, 16, 16 };
/*     */   
/*  35 */   protected int[] calcAut1000 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 7, 8, 9, 9, 10, 11, 12, 12, 12, 13, 13, 14, 14, 14 };
/*     */   
/*  37 */   protected int[] calcAut1100 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 7, 8, 9, 10, 10, 10, 11, 11, 12, 12, 12 };
/*     */   
/*  39 */   protected int[] calcAut1200 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10 };
/*     */   
/*  41 */   protected int[] calcAut1300 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisDCM15(ProtocoloUPS protocolo)
/*     */   {
/*  51 */     super(protocolo);
/*  52 */     int modelo = 0xF & protocolo.getCabecalhoPacote();
/*  53 */     protocolo.setModeloUPS(modelo);
/*  54 */     instanciaGlobais();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisDCM15() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/*  68 */     this.frequenciaEntradaC = 101700.0F;
/*     */     
/*  70 */     this.tensaoSaida220F1MB = 3.0F;
/*     */     
/*  72 */     this.tensaoSaida220F2MB = 12.0F;
/*     */     
/*  74 */     this.tensaoSaida220F1MR = 2.93F;
/*     */     
/*  76 */     this.tensaoSaida220F2MR = 13.0F;
/*     */     
/*  78 */     this.tensaoSaida110F1MB = 1.465F;
/*     */     
/*  80 */     this.tensaoSaida110F2MB = 16.0F;
/*     */     
/*  82 */     this.tensaoSaida110F1MR = 1.465F;
/*     */     
/*  84 */     this.tensaoSaida110F2MR = 12.0F;
/*     */     
/*  86 */     this.tensaoEntradaOffset = 50.0F;
/*     */     
/*  88 */     this.comparadorTensaoEntrada = 194;
/*     */     
/*  90 */     this.tensaoEntradaF1193 = 2.1417F;
/*     */     
/*  92 */     this.tensaoEntradaF2193 = 23.09F;
/*     */     
/*  94 */     this.tensaoEntradaF1194 = 2.1417F;
/*     */     
/*  96 */     this.tensaoEntradaF2194 = 23.09F;
/*     */     
/*  98 */     this.tensaoBateriaF1 = 0.4286F;
/*     */     
/* 100 */     this.tensaoBateriaF2 = 5.5714F;
/*     */     
/* 102 */     this.correnteSaida220F1MB = 0.08237232F;
/*     */     
/* 104 */     this.correnteSaida220F2MB = 0.382F;
/*     */     
/* 106 */     this.correnteSaida220F1MR = 0.08237232F;
/*     */     
/* 108 */     this.correnteSaida220F2MR = 0.382F;
/*     */     
/* 110 */     this.correnteSaida110F1MB = 0.08620689F;
/*     */     
/* 112 */     this.correnteSaida110F2MB = 0.1F;
/*     */     
/* 114 */     this.correnteSaida110F1MR = 0.08620689F;
/*     */     
/* 116 */     this.correnteSaida110F2MR = 0.1F;
/*     */     
/* 118 */     this.potenciaAparenteOffset = 100.0F;
/*     */     
/* 120 */     this.potenciaAparente220F1MR = 0.0F;
/*     */     
/* 122 */     this.potenciaAparente220F2MR = 0.0F;
/*     */     
/* 124 */     this.potenciaAparente220F1MB = 0.0F;
/*     */     
/* 126 */     this.potenciaAparente220F2MB = 0.0F;
/*     */     
/* 128 */     this.potenciaAparente110F1MR = 0.0F;
/*     */     
/* 130 */     this.potenciaAparente110F2MR = 0.0F;
/*     */     
/* 132 */     this.potenciaAparente110F1MB = 0.0F;
/*     */     
/* 134 */     this.potenciaAparente110F2MB = 0.0F;
/*     */     
/* 136 */     this.potenciaReal220F1MR = 0.2378F;
/*     */     
/* 138 */     this.potenciaReal220F2MR = 178.1F;
/*     */     
/* 140 */     this.potenciaReal220F1MB = 0.2664F;
/*     */     
/* 142 */     this.potenciaReal220F2MB = 148.45F;
/*     */     
/* 144 */     this.potenciaReal110F1MR = 0.1227F;
/*     */     
/* 146 */     this.potenciaReal110F2MR = 68.906F;
/*     */     
/* 148 */     this.potenciaReal110F1MB = 0.1367F;
/*     */     
/* 150 */     this.potenciaReal110F2MB = 52.747F;
/*     */     
/* 152 */     this.correnteEntradaF1MR = 0.0F;
/*     */     
/* 154 */     this.correnteEntradaF2MR = 0.0F;
/*     */     
/* 156 */     this.bateriaMaximoMR = 84.0F;
/*     */     
/* 158 */     this.bateriaMinimoMR = 63.0F;
/*     */     
/* 160 */     this.bateriaMaximoMB = 84.0F;
/*     */     
/* 162 */     this.bateriaMinimoMB = 63.0F;
/*     */     
/* 164 */     this.comparadorAutonomiaBateria = 5;
/*     */     
/* 166 */     this.comparadorContadorAutonomiaBaixa = 50;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int tensaoBateriaBruta)
/*     */   {
/* 178 */     if (this.potenciaReal <= 20.0F) {
/* 179 */       this.autonomiaBateria = 170;
/* 180 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 183 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 186 */       if (tensaoBateriaBruta > 177) {
/* 187 */         this.autonomiaBateria = 164;
/*     */       }
/* 189 */       if (tensaoBateriaBruta < 141) {
/* 190 */         this.autonomiaBateria = 0;
/*     */       }
/* 192 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 195 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 198 */       if (tensaoBateriaBruta > 177) {
/* 199 */         this.autonomiaBateria = 95;
/*     */       }
/* 201 */       if (tensaoBateriaBruta < 141) {
/* 202 */         this.autonomiaBateria = 0;
/*     */       }
/* 204 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 207 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 210 */       if (tensaoBateriaBruta > 177) {
/* 211 */         this.autonomiaBateria = 64;
/*     */       }
/* 213 */       if (tensaoBateriaBruta < 141) {
/* 214 */         this.autonomiaBateria = 0;
/*     */       }
/* 216 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 219 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 222 */       if (tensaoBateriaBruta > 172) {
/* 223 */         this.autonomiaBateria = 44;
/*     */       }
/* 225 */       if (tensaoBateriaBruta < 141) {
/* 226 */         this.autonomiaBateria = 0;
/*     */       }
/* 228 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 231 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 234 */       if (tensaoBateriaBruta > 170) {
/* 235 */         this.autonomiaBateria = 36;
/*     */       }
/* 237 */       if (tensaoBateriaBruta < 141) {
/* 238 */         this.autonomiaBateria = 0;
/*     */       }
/* 240 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 243 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 246 */       if (tensaoBateriaBruta > 173) {
/* 247 */         this.autonomiaBateria = 28;
/*     */       }
/* 249 */       if (tensaoBateriaBruta < 141) {
/* 250 */         this.autonomiaBateria = 0;
/*     */       }
/* 252 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 255 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 258 */       if (tensaoBateriaBruta > 172) {
/* 259 */         this.autonomiaBateria = 22;
/*     */       }
/* 261 */       if (tensaoBateriaBruta < 141) {
/* 262 */         this.autonomiaBateria = 0;
/*     */       }
/* 264 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 850.0F))
/*     */     {
/*     */       try {
/* 267 */         this.autonomiaBateria = this.calcAut0800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException7) {}
/* 270 */       if (tensaoBateriaBruta > 172) {
/* 271 */         this.autonomiaBateria = 20;
/*     */       }
/* 273 */       if (tensaoBateriaBruta < 141) {
/* 274 */         this.autonomiaBateria = 0;
/*     */       }
/* 276 */     } else if ((this.potenciaReal > 850.0F) && (this.potenciaReal <= 950.0F))
/*     */     {
/*     */       try {
/* 279 */         this.autonomiaBateria = this.calcAut0900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException8) {}
/* 282 */       if (tensaoBateriaBruta > 172) {
/* 283 */         this.autonomiaBateria = 16;
/*     */       }
/* 285 */       if (tensaoBateriaBruta < 141) {
/* 286 */         this.autonomiaBateria = 0;
/*     */       }
/* 288 */     } else if ((this.potenciaReal > 950.0F) && (this.potenciaReal <= 1050.0F))
/*     */     {
/*     */       try {
/* 291 */         this.autonomiaBateria = this.calcAut1000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException9) {}
/* 294 */       if (tensaoBateriaBruta > 172) {
/* 295 */         this.autonomiaBateria = 14;
/*     */       }
/* 297 */       if (tensaoBateriaBruta < 141) {
/* 298 */         this.autonomiaBateria = 0;
/*     */       }
/* 300 */     } else if ((this.potenciaReal > 1050.0F) && (this.potenciaReal <= 1150.0F))
/*     */     {
/*     */       try {
/* 303 */         this.autonomiaBateria = this.calcAut1100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException10) {}
/* 306 */       if (tensaoBateriaBruta > 172) {
/* 307 */         this.autonomiaBateria = 22;
/*     */       }
/* 309 */       if (tensaoBateriaBruta < 141) {
/* 310 */         this.autonomiaBateria = 0;
/*     */       }
/* 312 */     } else if ((this.potenciaReal > 1150.0F) && (this.potenciaReal <= 1250.0F))
/*     */     {
/*     */       try {
/* 315 */         this.autonomiaBateria = this.calcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException11) {}
/* 318 */       if (tensaoBateriaBruta > 172) {
/* 319 */         this.autonomiaBateria = 12;
/*     */       }
/* 321 */       if (tensaoBateriaBruta < 141) {
/* 322 */         this.autonomiaBateria = 0;
/*     */       }
/* 324 */     } else if (this.potenciaReal > 1250.0F)
/*     */     {
/*     */       try {
/* 327 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException12) {}
/* 330 */       if (tensaoBateriaBruta > 172) {
/* 331 */         this.autonomiaBateria = 8;
/*     */       }
/* 333 */       if (tensaoBateriaBruta < 141) {
/* 334 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 338 */     if (this.expansorBateria > 0) {
/* 339 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*     */     }
/*     */     
/* 342 */     this.autonomiaBateria /= 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void notifica()
/*     */   {
/* 354 */     super.notifica();
/* 355 */     setModoBypass(this.protocoloUPS.isBypassAtivado());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida220(boolean tensaoSaida220)
/*     */   {
/* 364 */     this.tensaoSaida220 = (!tensaoSaida220);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 374 */     if (!this.modoRede)
/*     */     {
/* 376 */       if (this.tensaoSaida220) {
/* 377 */         this.tensaoSaida = 220.0F;
/*     */       } else {
/* 379 */         this.tensaoSaida = 115.0F;
/*     */       }
/*     */       
/*     */     }
/* 383 */     else if (this.tensaoSaida220) {
/* 384 */       this.tensaoSaida = 220.0F;
/*     */     } else {
/* 386 */       this.tensaoSaida = 115.0F;
/*     */     }
/* 388 */     if (!this.modoBateria) {
/* 389 */       this.tensaoSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*     */   {
/* 396 */     if (this.tensaoEntrada > 180.0F) {
/* 397 */       this.tensaoEntrada220 = true;
/*     */     } else {
/* 399 */       this.tensaoEntrada220 = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntradaNominal(int tensaoEntrada)
/*     */   {
/* 408 */     if (this.tensaoEntrada > 180.0F) {
/* 409 */       this.tensaoEntradaNominal = 220.0F;
/*     */     }
/*     */     else {
/* 412 */       this.tensaoEntradaNominal = 115.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 424 */     if (tensaoEntrada >= this.comparadorTensaoEntrada) {
/* 425 */       this.tensaoEntrada = (tensaoEntrada * this.tensaoEntradaF1194 + this.tensaoEntradaF2194);
/*     */     } else {
/* 427 */       this.tensaoEntrada = (tensaoEntrada * this.tensaoEntradaF1193 + this.tensaoEntradaF2193);
/*     */     }
/* 429 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/* 430 */       this.tensaoEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 440 */     if (!this.modoRede)
/*     */     {
/* 442 */       if (this.tensaoSaida220) {
/* 443 */         this.potenciaReal = (potenciaReal * this.potenciaReal220F1MB + this.potenciaReal220F2MB);
/*     */       } else {
/* 445 */         this.potenciaReal = (potenciaReal * this.potenciaReal110F1MB + this.potenciaReal110F2MB);
/*     */       }
/*     */       
/*     */     }
/* 449 */     else if (this.tensaoSaida220) {
/* 450 */       this.potenciaReal = (potenciaReal * this.potenciaReal220F1MR + this.potenciaReal220F2MR);
/*     */     } else {
/* 452 */       this.potenciaReal = (potenciaReal * this.potenciaReal110F1MR + this.potenciaReal110F2MR);
/*     */     }
/* 454 */     if (potenciaReal < this.potenciaAparenteOffset) {
/* 455 */       this.potenciaReal = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPotenciaAparente(int potenciaAparente)
/*     */   {
/* 466 */     this.potenciaAparente = (1.25F * this.potenciaReal);
/*     */     
/* 468 */     if (this.potenciaAparente < this.potenciaAparenteOffset) {
/* 469 */       this.potenciaAparente = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteEntrada(int correnteEntrada)
/*     */   {
/* 480 */     if (!this.modoRede) {
/* 481 */       this.correnteEntrada = 0.0F;
/*     */     } else {
/* 483 */       this.correnteEntrada = (this.potenciaReal / this.tensaoEntrada);
/*     */     }
/* 485 */     if (this.tensaoEntrada < this.tensaoEntradaOffset) {
/* 486 */       this.correnteEntrada = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 496 */     if (!this.modoRede)
/*     */     {
/* 498 */       if (this.tensaoSaida220) {
/* 499 */         this.correnteSaida = (correnteSaida * this.correnteSaida220F1MB + this.correnteSaida220F2MB);
/*     */       } else {
/* 501 */         this.correnteSaida = (correnteSaida * this.correnteSaida110F1MB + this.correnteSaida110F2MB);
/*     */       }
/*     */       
/*     */     }
/* 505 */     else if (this.tensaoSaida220) {
/* 506 */       this.correnteSaida = (correnteSaida * this.correnteSaida220F1MR + this.correnteSaida220F2MR);
/*     */     }
/*     */     else {
/* 509 */       this.correnteSaida = (correnteSaida * this.correnteSaida110F1MR + this.correnteSaida110F2MR);
/*     */     }
/*     */     
/* 512 */     if (this.correnteSaida < 0.8D) {
/* 513 */       this.correnteSaida = 0.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCargaElevada(boolean cargaElevada)
/*     */   {
/* 523 */     this.cargaElevada = (this.potenciaReal > 1600.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTemperaturaElevada(boolean temperaturaElevada)
/*     */   {
/* 534 */     if (this.temperaturaUPS > this.temperaturaCritica)
/*     */     {
/* 536 */       this.cntSobreTemperatura += 1;
/* 537 */       if (this.cntSobreTemperatura > 5) {
/* 538 */         this.temperaturaElevada = true;
/*     */       }
/*     */     }
/*     */     else {
/* 542 */       this.cntSobreTemperatura = 0;
/* 543 */       this.temperaturaElevada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModoRede(boolean modoRede)
/*     */   {
/* 554 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 557 */     int mesAtual = calendar.get(2) + 1;
/* 558 */     int anoAtual = calendar.get(1);
/*     */     
/* 560 */     if (!this.modoRede)
/*     */     {
/* 562 */       this.modoRede = modoRede;
/* 563 */       if (this.modoRede)
/*     */       {
/* 565 */         Evento evt = new Evento(3, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/* 566 */         ProtocoloSolis solis = (ProtocoloSolis)this.protocoloUPS;
/* 567 */         solis.addEvento(evt);
/*     */       }
/*     */     }
/*     */     
/* 571 */     this.modoRede = modoRede;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntradaLigada(boolean entradaLigada)
/*     */   {
/* 579 */     if (this.tensaoEntrada > 0.0F)
/*     */     {
/* 581 */       this.entradaLigada = true;
/*     */     }
/*     */     else {
/* 584 */       this.entradaLigada = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean ligaEntrada()
/*     */   {
/* 594 */     this.protocoloUPS.ligaEntrada();
/* 595 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean desligaEntrada()
/*     */   {
/* 603 */     this.protocoloUPS.desligaEntrada();
/* 604 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAutoTesteBateriaAvaliable()
/*     */   {
/* 611 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 618 */     if (this.tensaoBateria < 70.0F) {
/* 619 */       this.bateriaBaixa = true;
/*     */     } else {
/* 621 */       this.bateriaBaixa = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
/*     */   {
/* 633 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/* 636 */     int mesAtual = calendar.get(2) + 1;
/* 637 */     int anoAtual = calendar.get(1);
/*     */     
/* 639 */     Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
/* 640 */     ProtocoloSolis solis = (ProtocoloSolis)this.protocoloUPS;
/* 641 */     solis.addEvento(evt);
/*     */     
/* 643 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisDCM15.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */