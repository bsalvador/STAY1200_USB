/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SolisM15
/*     */   extends SolisM11
/*     */ {
/*  16 */   protected int[] calcAut0100 = { 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 19, 22, 24, 27, 30, 33, 37, 42, 44, 50, 55, 60, 65, 70, 75, 80, 88, 93, 95, 100 };
/*     */   
/*  18 */   protected int[] calcAut0200 = { 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 20, 22, 25, 27, 31, 35, 38, 42, 46, 50, 55, 61, 66, 71, 76, 83, 89 };
/*     */   
/*  20 */   protected int[] calcAut0300 = { 1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 9, 10, 11, 12, 13, 15, 18, 19, 22, 24, 26, 30, 33, 38, 40, 45, 50, 55, 60, 66, 70 };
/*     */   
/*  22 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 2, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 10, 11, 13, 16, 17, 19, 21, 23, 25, 28, 34, 36, 39, 45, 50, 55 };
/*     */   
/*  24 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 9, 10, 12, 14, 15, 17, 18, 20, 21, 25, 28, 30, 34, 39 };
/*     */   
/*  26 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7, 8, 8, 9, 10, 12, 13, 15, 16, 18, 19, 22, 25, 26, 28, 30 };
/*     */   
/*  28 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 11, 12, 13, 14, 17, 17, 20, 22, 24, 26 };
/*     */   
/*  30 */   protected int[] calcAut0800 = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 7, 8, 10, 11, 12, 13, 15, 16, 18, 20, 22, 23 };
/*     */   
/*  32 */   protected int[] calcAut0900 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 7, 7, 9, 10, 11, 13, 14, 15, 16, 18, 19 };
/*     */   
/*  34 */   protected int[] calcAut1000 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 7, 7, 8, 10, 11, 13, 14, 15, 15, 16, 17 };
/*     */   
/*  36 */   protected int[] calcAut1100 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 13, 14, 13, 14 };
/*     */   
/*  38 */   protected int[] calcAut1200 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 10, 12, 13, 12 };
/*     */   
/*  40 */   protected int[] calcAut1300 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 8, 9, 11, 12 };
/*     */   
/*  42 */   protected int[] calcAut1400 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 9 };
/*     */   
/*  44 */   protected int[] calcAut1500 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8 };
/*     */   
/*  46 */   protected int[] calcAut1600 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8 };
/*     */   
/*  48 */   protected int[] calcAut1700 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8 };
/*     */   
/*  50 */   protected int[] calcAut1800 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8 };
/*     */   
/*  52 */   protected int[] calcAut1900 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7 };
/*     */   
/*  54 */   protected int[] calcAut2000 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6 };
/*     */   
/*  56 */   protected int[] calcAut2100 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */   protected int[] xcalcAut0100 = { 1, 2, 5, 5, 8, 11, 14, 16, 19, 22, 36, 40, 44, 48, 52, 56, 64, 76, 88, 96, 145, 162, 178, 199, 226, 237, 270, 297, 324, 351, 378, 405, 432, 475, 502, 513, 540 };
/*     */   
/*  64 */   protected int[] xcalcAut0200 = { 1, 2, 3, 5, 8, 11, 14, 16, 19, 22, 36, 40, 44, 48, 52, 56, 60, 72, 80, 88, 135, 145, 167, 189, 205, 226, 248, 270, 297, 329, 356, 383, 410, 448, 480 };
/*     */   
/*  66 */   protected int[] xcalcAut0300 = { 1, 2, 3, 5, 6, 8, 11, 14, 16, 19, 32, 32, 36, 40, 44, 48, 52, 60, 72, 76, 118, 129, 140, 162, 178, 205, 216, 243, 270, 297, 324, 356, 378 };
/*     */   
/*  68 */   protected int[] xcalcAut0400 = { 1, 2, 3, 4, 5, 6, 8, 11, 14, 16, 28, 28, 32, 36, 40, 40, 44, 52, 64, 68, 102, 113, 124, 135, 151, 183, 194, 210, 243, 270, 297 };
/*     */   
/*  70 */   protected int[] xcalcAut0500 = { 1, 2, 3, 4, 5, 6, 8, 11, 11, 14, 24, 24, 28, 32, 32, 36, 40, 48, 56, 60, 91, 97, 108, 113, 135, 151, 162, 183, 210 };
/*     */   
/*  72 */   protected int[] xcalcAut0600 = { 1, 2, 3, 4, 5, 6, 8, 11, 11, 11, 20, 20, 24, 28, 32, 32, 36, 40, 48, 52, 81, 86, 97, 102, 118, 135, 140, 151, 162 };
/*     */   
/*  74 */   protected int[] xcalcAut0700 = { 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 16, 16, 20, 24, 28, 28, 32, 36, 44, 48, 70, 75, 91, 91, 108, 118, 129, 140 };
/*     */   
/*  76 */   protected int[] xcalcAut0800 = { 1, 2, 2, 3, 4, 5, 5, 8, 8, 8, 16, 16, 20, 20, 24, 28, 28, 32, 40, 44, 64, 70, 81, 86, 97, 108, 118, 124 };
/*     */   
/*  78 */   protected int[] xcalcAut0900 = { 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 12, 12, 16, 16, 20, 24, 28, 28, 36, 40, 59, 70, 75, 81, 86, 97, 102 };
/*     */   
/*  80 */   protected int[] xcalcAut1000 = { 1, 2, 2, 3, 3, 4, 4, 5, 5, 8, 10, 12, 16, 16, 20, 24, 28, 28, 32, 40, 59, 70, 75, 81, 81, 86, 91 };
/*     */   
/*  82 */   protected int[] xcalcAut1100 = { 1, 2, 2, 3, 3, 4, 4, 5, 6, 8, 10, 12, 16, 16, 20, 24, 28, 28, 32, 36, 54, 59, 70, 75, 70, 75 };
/*     */   
/*  84 */   protected int[] xcalcAut1200 = { 2, 2, 2, 2, 2, 2, 2, 5, 6, 8, 10, 12, 16, 18, 20, 22, 24, 26, 28, 36, 48, 54, 60, 65, 70 };
/*     */   
/*  86 */   protected int[] xcalcAut1300 = { 1, 2, 2, 3, 3, 4, 4, 5, 6, 8, 10, 12, 15, 16, 18, 20, 22, 24, 28, 32, 43, 48, 58, 64 };
/*     */   
/*  88 */   protected int[] xcalcAut1400 = { 1, 2, 2, 3, 3, 4, 4, 4, 5, 6, 8, 10, 12, 16, 20, 24, 26, 28, 30, 32, 42, 48 };
/*     */   
/*  90 */   protected int[] xcalcAut1500 = { 1, 2, 2, 3, 3, 4, 4, 4, 5, 6, 8, 10, 12, 15, 18, 20, 24, 28, 30, 32 };
/*     */   
/*  92 */   protected int[] xcalcAut1600 = { 1, 2, 2, 3, 3, 4, 4, 4, 5, 6, 8, 10, 12, 16, 18, 22, 24, 28, 30, 32 };
/*     */   
/*  94 */   protected int[] xcalcAut1700 = { 1, 2, 2, 3, 3, 4, 4, 4, 5, 6, 8, 10, 12, 16, 18, 20, 22, 24, 28, 32 };
/*     */   
/*  96 */   protected int[] xcalcAut1800 = { 1, 2, 2, 3, 3, 4, 4, 4, 5, 6, 8, 10, 12, 16, 18, 20, 22, 24, 28, 32 };
/*     */   
/*  98 */   protected int[] xcalcAut1900 = { 1, 2, 2, 3, 3, 4, 5, 5, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 28 };
/*     */   
/* 100 */   protected int[] xcalcAut2000 = { 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 8, 10, 12, 16, 18, 20, 22, 24 };
/*     */   
/* 102 */   protected int[] xcalcAut2100 = { 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 8, 10, 12, 15, 16, 18, 20 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM15(ProtocoloUPS protocolo)
/*     */   {
/* 112 */     super(protocolo);
/* 113 */     protocolo.setModeloUPS(13);
/* 114 */     instanciaGlobais();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM15() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/* 128 */     this.potenciaNominalVA = 3000;
/*     */     
/* 130 */     this.potenciaNominalW = 3000;
/*     */     
/* 132 */     this.frequenciaEntradaC = 101800.0F;
/*     */     
/* 134 */     this.correnteEntradaF1MR = 35.0F;
/*     */     
/* 136 */     this.correnteEntradaF2MR = 1000.0F;
/*     */     
/* 138 */     this.tensaoEntradaOffset = 30.0F;
/*     */     
/* 140 */     this.comparadorTensaoEntrada = 194;
/*     */     
/* 142 */     this.tensaoEntradaF1193 = 1.127F;
/*     */     
/* 144 */     this.tensaoEntradaF2193 = 12.0F;
/*     */     
/* 146 */     this.tensaoEntradaF1194 = 2.5F;
/*     */     
/* 148 */     this.tensaoEntradaF2194 = -250.0F;
/*     */     
/* 150 */     this.tensaoBateriaF1 = 0.2840909F;
/*     */     
/* 152 */     this.tensaoBateriaF2 = 0.0F;
/*     */     
/* 154 */     this.bateriaMaximoMR = 56.0F;
/*     */     
/* 156 */     this.bateriaMinimoMR = 46.0F;
/*     */     
/* 158 */     this.bateriaMaximoMB = 52.0F;
/*     */     
/* 160 */     this.bateriaMinimoMB = 44.0F;
/*     */     
/* 162 */     this.comparadorAutonomiaBateria = 5;
/*     */     
/* 164 */     this.comparadorContadorAutonomiaBaixa = 5;
/*     */     
/* 166 */     this.correnteSaida220F1MR = 0.0625F;
/*     */     
/* 168 */     this.correnteSaida220F2MR = 0.4F;
/*     */     
/* 170 */     this.correnteSaida220F1MB = 0.122699395F;
/*     */     
/* 172 */     this.correnteSaida220F2MB = 0.25F;
/*     */     
/* 174 */     this.correnteSaida110F1MR = 0.122699395F;
/*     */     
/* 176 */     this.correnteSaida110F2MR = 0.25F;
/*     */     
/* 178 */     this.correnteSaida110F1MB = 0.122699395F;
/*     */     
/* 180 */     this.correnteSaida110F2MB = 0.25F;
/*     */     
/* 182 */     this.tensaoSaida220F1MR = 2.73F;
/*     */     
/* 184 */     this.tensaoSaida220F2MR = 25.0F;
/*     */     
/* 186 */     this.tensaoSaida220F1MB = 2.73F;
/*     */     
/* 188 */     this.tensaoSaida220F2MB = 30.0F;
/*     */     
/* 190 */     this.tensaoSaida110F1MR = 1.41F;
/*     */     
/* 192 */     this.tensaoSaida110F2MR = 13.0F;
/*     */     
/* 194 */     this.tensaoSaida110F1MB = 1.4F;
/*     */     
/* 196 */     this.tensaoSaida110F2MB = 17.0F;
/*     */     
/* 198 */     this.potenciaReal220F1MR = 0.20920502F;
/*     */     
/* 200 */     this.potenciaReal220F2MR = 52.0F;
/*     */     
/* 202 */     this.potenciaReal220F1MB = 0.2197802F;
/*     */     
/* 204 */     this.potenciaReal220F2MB = 55.0F;
/*     */     
/* 206 */     this.potenciaReal110F1MR = 0.20533882F;
/*     */     
/* 208 */     this.potenciaReal110F2MR = 19.0F;
/*     */     
/* 210 */     this.potenciaReal110F1MB = 0.2197802F;
/*     */     
/* 212 */     this.potenciaReal110F2MB = 17.0F;
/*     */     
/* 214 */     this.potenciaAparenteOffset = 55.0F;
/*     */     
/* 216 */     this.potenciaAparente220F1MR = 0.20920502F;
/*     */     
/* 218 */     this.potenciaAparente220F2MR = 52.0F;
/*     */     
/* 220 */     this.potenciaAparente220F1MB = 0.2197802F;
/*     */     
/* 222 */     this.potenciaAparente220F2MB = 55.0F;
/*     */     
/* 224 */     this.potenciaAparente110F1MR = 0.19417475F;
/*     */     
/* 226 */     this.potenciaAparente110F2MR = 29.0F;
/*     */     
/* 228 */     this.potenciaAparente110F1MB = 0.20833333F;
/*     */     
/* 230 */     this.potenciaAparente110F2MB = 26.0F;
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
/* 242 */     if (this.expansorBateria == 80) {
/* 243 */       calcAutonomiaExp(tensaoBateriaBruta);
/*     */     } else {
/* 245 */       calcAutonomia(tensaoBateriaBruta);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void calcAutonomia(int tensaoBateriaBruta)
/*     */   {
/* 253 */     if (this.potenciaReal <= 20.0F) {
/* 254 */       this.autonomiaBateria = 170;
/* 255 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 258 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 261 */       if (tensaoBateriaBruta > 177) {
/* 262 */         this.autonomiaBateria = 95;
/*     */       }
/* 264 */       if (tensaoBateriaBruta < 141) {
/* 265 */         this.autonomiaBateria = 0;
/*     */       }
/* 267 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 270 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 273 */       if (tensaoBateriaBruta > 175) {
/* 274 */         this.autonomiaBateria = 89;
/*     */       }
/* 276 */       if (tensaoBateriaBruta < 141) {
/* 277 */         this.autonomiaBateria = 0;
/*     */       }
/* 279 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 282 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 285 */       if (tensaoBateriaBruta > 173) {
/* 286 */         this.autonomiaBateria = 70;
/*     */       }
/* 288 */       if (tensaoBateriaBruta < 141) {
/* 289 */         this.autonomiaBateria = 0;
/*     */       }
/* 291 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 294 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 297 */       if (tensaoBateriaBruta > 171) {
/* 298 */         this.autonomiaBateria = 55;
/*     */       }
/* 300 */       if (tensaoBateriaBruta < 141) {
/* 301 */         this.autonomiaBateria = 0;
/*     */       }
/* 303 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 306 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 309 */       if (tensaoBateriaBruta > 169) {
/* 310 */         this.autonomiaBateria = 39;
/*     */       }
/* 312 */       if (tensaoBateriaBruta < 141) {
/* 313 */         this.autonomiaBateria = 0;
/*     */       }
/* 315 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 318 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 321 */       if (tensaoBateriaBruta > 169) {
/* 322 */         this.autonomiaBateria = 30;
/*     */       }
/* 324 */       if (tensaoBateriaBruta < 141) {
/* 325 */         this.autonomiaBateria = 0;
/*     */       }
/* 327 */     } else if ((this.potenciaReal > 650.0F) && (this.potenciaReal <= 750.0F))
/*     */     {
/*     */       try {
/* 330 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 333 */       if (tensaoBateriaBruta > 168) {
/* 334 */         this.autonomiaBateria = 23;
/*     */       }
/* 336 */       if (tensaoBateriaBruta < 141) {
/* 337 */         this.autonomiaBateria = 0;
/*     */       }
/* 339 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 850.0F))
/*     */     {
/*     */       try {
/* 342 */         this.autonomiaBateria = this.calcAut0800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException7) {}
/* 345 */       if (tensaoBateriaBruta > 168) {
/* 346 */         this.autonomiaBateria = 23;
/*     */       }
/* 348 */       if (tensaoBateriaBruta < 141) {
/* 349 */         this.autonomiaBateria = 0;
/*     */       }
/* 351 */     } else if ((this.potenciaReal > 850.0F) && (this.potenciaReal <= 950.0F))
/*     */     {
/*     */       try {
/* 354 */         this.autonomiaBateria = this.calcAut0900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException8) {}
/* 357 */       if (tensaoBateriaBruta > 167) {
/* 358 */         this.autonomiaBateria = 19;
/*     */       }
/* 360 */       if (tensaoBateriaBruta < 141) {
/* 361 */         this.autonomiaBateria = 0;
/*     */       }
/* 363 */     } else if ((this.potenciaReal > 950.0F) && (this.potenciaReal <= 1050.0F))
/*     */     {
/*     */       try {
/* 366 */         this.autonomiaBateria = this.calcAut1000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException9) {}
/* 369 */       if (tensaoBateriaBruta > 167) {
/* 370 */         this.autonomiaBateria = 17;
/*     */       }
/* 372 */       if (tensaoBateriaBruta < 141) {
/* 373 */         this.autonomiaBateria = 0;
/*     */       }
/* 375 */     } else if ((this.potenciaReal > 1050.0F) && (this.potenciaReal <= 1150.0F))
/*     */     {
/*     */       try {
/* 378 */         this.autonomiaBateria = this.calcAut1100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException10) {}
/* 381 */       if (tensaoBateriaBruta > 166) {
/* 382 */         this.autonomiaBateria = 14;
/*     */       }
/* 384 */       if (tensaoBateriaBruta < 141) {
/* 385 */         this.autonomiaBateria = 0;
/*     */       }
/* 387 */     } else if ((this.potenciaReal > 1150.0F) && (this.potenciaReal <= 1250.0F))
/*     */     {
/*     */       try {
/* 390 */         this.autonomiaBateria = this.calcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException11) {}
/* 393 */       if (tensaoBateriaBruta > 165) {
/* 394 */         this.autonomiaBateria = 12;
/*     */       }
/* 396 */       if (tensaoBateriaBruta < 141) {
/* 397 */         this.autonomiaBateria = 0;
/*     */       }
/* 399 */     } else if ((this.potenciaReal > 1250.0F) && (this.potenciaReal <= 1350.0F))
/*     */     {
/*     */       try {
/* 402 */         this.autonomiaBateria = this.calcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException12) {}
/* 405 */       if (tensaoBateriaBruta > 162) {
/* 406 */         this.autonomiaBateria = 9;
/*     */       }
/* 408 */       if (tensaoBateriaBruta < 141) {
/* 409 */         this.autonomiaBateria = 0;
/*     */       }
/* 411 */     } else if ((this.potenciaReal > 1350.0F) && (this.potenciaReal <= 1450.0F))
/*     */     {
/*     */       try {
/* 414 */         this.autonomiaBateria = this.calcAut1300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException13) {}
/* 417 */       if (tensaoBateriaBruta > 162) {
/* 418 */         this.autonomiaBateria = 9;
/*     */       }
/* 420 */       if (tensaoBateriaBruta < 141) {
/* 421 */         this.autonomiaBateria = 0;
/*     */       }
/* 423 */     } else if ((this.potenciaReal > 1450.0F) && (this.potenciaReal <= 1550.0F))
/*     */     {
/*     */       try {
/* 426 */         this.autonomiaBateria = this.calcAut1400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException14) {}
/* 429 */       if (tensaoBateriaBruta > 160) {
/* 430 */         this.autonomiaBateria = 8;
/*     */       }
/* 432 */       if (tensaoBateriaBruta < 141) {
/* 433 */         this.autonomiaBateria = 0;
/*     */       }
/* 435 */     } else if ((this.potenciaReal > 1550.0F) && (this.potenciaReal <= 1650.0F))
/*     */     {
/*     */       try {
/* 438 */         this.autonomiaBateria = this.calcAut1500[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException15) {}
/* 441 */       if (tensaoBateriaBruta > 160) {
/* 442 */         this.autonomiaBateria = 8;
/*     */       }
/* 444 */       if (tensaoBateriaBruta < 141) {
/* 445 */         this.autonomiaBateria = 0;
/*     */       }
/* 447 */     } else if ((this.potenciaReal > 1650.0F) && (this.potenciaReal <= 1750.0F))
/*     */     {
/*     */       try {
/* 450 */         this.autonomiaBateria = this.calcAut1600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException16) {}
/* 453 */       if (tensaoBateriaBruta > 160) {
/* 454 */         this.autonomiaBateria = 8;
/*     */       }
/* 456 */       if (tensaoBateriaBruta < 141) {
/* 457 */         this.autonomiaBateria = 0;
/*     */       }
/* 459 */     } else if ((this.potenciaReal > 1750.0F) && (this.potenciaReal <= 1850.0F))
/*     */     {
/*     */       try {
/* 462 */         this.autonomiaBateria = this.calcAut1700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException17) {}
/* 465 */       if (tensaoBateriaBruta > 160) {
/* 466 */         this.autonomiaBateria = 8;
/*     */       }
/* 468 */       if (tensaoBateriaBruta < 141) {
/* 469 */         this.autonomiaBateria = 0;
/*     */       }
/* 471 */     } else if ((this.potenciaReal > 1750.0F) && (this.potenciaReal <= 1850.0F))
/*     */     {
/*     */       try {
/* 474 */         this.autonomiaBateria = this.calcAut1800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException18) {}
/* 477 */       if (tensaoBateriaBruta > 160) {
/* 478 */         this.autonomiaBateria = 8;
/*     */       }
/* 480 */       if (tensaoBateriaBruta < 141) {
/* 481 */         this.autonomiaBateria = 0;
/*     */       }
/* 483 */     } else if ((this.potenciaReal > 1850.0F) && (this.potenciaReal <= 1950.0F))
/*     */     {
/*     */       try {
/* 486 */         this.autonomiaBateria = this.calcAut1900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException19) {}
/* 489 */       if (tensaoBateriaBruta > 159) {
/* 490 */         this.autonomiaBateria = 7;
/*     */       }
/* 492 */       if (tensaoBateriaBruta < 141) {
/* 493 */         this.autonomiaBateria = 0;
/*     */       }
/* 495 */     } else if ((this.potenciaReal > 1950.0F) && (this.potenciaReal <= 2050.0F))
/*     */     {
/*     */       try {
/* 498 */         this.autonomiaBateria = this.calcAut2000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException20) {}
/* 501 */       if (tensaoBateriaBruta > 158) {
/* 502 */         this.autonomiaBateria = 6;
/*     */       }
/* 504 */       if (tensaoBateriaBruta < 141) {
/* 505 */         this.autonomiaBateria = 0;
/*     */       }
/* 507 */     } else if (this.potenciaReal > 2050.0F)
/*     */     {
/*     */       try {
/* 510 */         this.autonomiaBateria = this.calcAut2100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException21) {}
/* 513 */       if (tensaoBateriaBruta > 157) {
/* 514 */         this.autonomiaBateria = 5;
/*     */       }
/* 516 */       if (tensaoBateriaBruta < 141) {
/* 517 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 521 */     if (this.expansorBateria > 0) {
/* 522 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 18) / 18);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void calcAutonomiaExp(int tensaoBateriaBruta)
/*     */   {
/* 530 */     if (this.potenciaReal <= 20.0F) {
/* 531 */       this.autonomiaBateria = 170;
/* 532 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 535 */         this.autonomiaBateria = this.xcalcAut0100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 538 */       if (tensaoBateriaBruta > 177) {
/* 539 */         this.autonomiaBateria = 95;
/*     */       }
/* 541 */       if (tensaoBateriaBruta < 141) {
/* 542 */         this.autonomiaBateria = 0;
/*     */       }
/* 544 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 547 */         this.autonomiaBateria = this.xcalcAut0200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 550 */       if (tensaoBateriaBruta > 175) {
/* 551 */         this.autonomiaBateria = 89;
/*     */       }
/* 553 */       if (tensaoBateriaBruta < 141) {
/* 554 */         this.autonomiaBateria = 0;
/*     */       }
/* 556 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 559 */         this.autonomiaBateria = this.xcalcAut0300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 562 */       if (tensaoBateriaBruta > 173) {
/* 563 */         this.autonomiaBateria = 70;
/*     */       }
/* 565 */       if (tensaoBateriaBruta < 141) {
/* 566 */         this.autonomiaBateria = 0;
/*     */       }
/* 568 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 571 */         this.autonomiaBateria = this.xcalcAut0400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 574 */       if (tensaoBateriaBruta > 171) {
/* 575 */         this.autonomiaBateria = 55;
/*     */       }
/* 577 */       if (tensaoBateriaBruta < 141) {
/* 578 */         this.autonomiaBateria = 0;
/*     */       }
/* 580 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 583 */         this.autonomiaBateria = this.xcalcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 586 */       if (tensaoBateriaBruta > 169) {
/* 587 */         this.autonomiaBateria = 39;
/*     */       }
/* 589 */       if (tensaoBateriaBruta < 141) {
/* 590 */         this.autonomiaBateria = 0;
/*     */       }
/* 592 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 595 */         this.autonomiaBateria = this.xcalcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 598 */       if (tensaoBateriaBruta > 169) {
/* 599 */         this.autonomiaBateria = 30;
/*     */       }
/* 601 */       if (tensaoBateriaBruta < 141) {
/* 602 */         this.autonomiaBateria = 0;
/*     */       }
/* 604 */     } else if ((this.potenciaReal > 650.0F) && (this.potenciaReal <= 750.0F))
/*     */     {
/*     */       try {
/* 607 */         this.autonomiaBateria = this.xcalcAut0700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 610 */       if (tensaoBateriaBruta > 168) {
/* 611 */         this.autonomiaBateria = 23;
/*     */       }
/* 613 */       if (tensaoBateriaBruta < 141) {
/* 614 */         this.autonomiaBateria = 0;
/*     */       }
/* 616 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 850.0F))
/*     */     {
/*     */       try {
/* 619 */         this.autonomiaBateria = this.xcalcAut0800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException7) {}
/*  89 */       if (tensaoBateriaBruta > 168)
/*     */       {
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
/* 623 */         this.autonomiaBateria = 23;
/*     */       }
/* 625 */       if (tensaoBateriaBruta < 141) {
/* 626 */         this.autonomiaBateria = 0;
/*     */       }
/* 628 */     } else if ((this.potenciaReal > 850.0F) && (this.potenciaReal <= 950.0F))
/*     */     {
/*     */       try {
/* 631 */         this.autonomiaBateria = this.xcalcAut0900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException8) {}
/* 634 */       if (tensaoBateriaBruta > 167) {
/* 635 */         this.autonomiaBateria = 19;
/*     */       }
/* 637 */       if (tensaoBateriaBruta < 141) {
/* 638 */         this.autonomiaBateria = 0;
/*     */       }
/* 640 */     } else if ((this.potenciaReal > 950.0F) && (this.potenciaReal <= 1050.0F))
/*     */     {
/*     */       try {
/* 643 */         this.autonomiaBateria = this.xcalcAut1000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException9) {}
/* 646 */       if (tensaoBateriaBruta > 167) {
/* 647 */         this.autonomiaBateria = 17;
/*     */       }
/* 649 */       if (tensaoBateriaBruta < 141) {
/* 650 */         this.autonomiaBateria = 0;
/*     */       }
/* 652 */     } else if ((this.potenciaReal > 1050.0F) && (this.potenciaReal <= 1150.0F))
/*     */     {
/*     */       try {
/* 655 */         this.autonomiaBateria = this.xcalcAut1100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException10) {}
/* 658 */       if (tensaoBateriaBruta > 166) {
/* 659 */         this.autonomiaBateria = 14;
/*     */       }
/* 661 */       if (tensaoBateriaBruta < 141) {
/* 662 */         this.autonomiaBateria = 0;
/*     */       }
/* 664 */     } else if ((this.potenciaReal > 1150.0F) && (this.potenciaReal <= 1250.0F))
/*     */     {
/*     */       try {
/* 667 */         this.autonomiaBateria = this.xcalcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException11) {}
/* 670 */       if (tensaoBateriaBruta > 165) {
/* 671 */         this.autonomiaBateria = 12;
/*     */       }
/* 673 */       if (tensaoBateriaBruta < 141) {
/* 674 */         this.autonomiaBateria = 0;
/*     */       }
/* 676 */     } else if ((this.potenciaReal > 1250.0F) && (this.potenciaReal <= 1350.0F))
/*     */     {
/*     */       try {
/* 679 */         this.autonomiaBateria = this.xcalcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException12) {}
/* 682 */       if (tensaoBateriaBruta > 162) {
/* 683 */         this.autonomiaBateria = 9;
/*     */       }
/* 685 */       if (tensaoBateriaBruta < 141) {
/* 686 */         this.autonomiaBateria = 0;
/*     */       }
/* 688 */     } else if ((this.potenciaReal > 1350.0F) && (this.potenciaReal <= 1450.0F))
/*     */     {
/*     */       try {
/* 691 */         this.autonomiaBateria = this.xcalcAut1300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException13) {}
/* 694 */       if (tensaoBateriaBruta > 162) {
/* 695 */         this.autonomiaBateria = 9;
/*     */       }
/* 697 */       if (tensaoBateriaBruta < 141) {
/* 698 */         this.autonomiaBateria = 0;
/*     */       }
/* 700 */     } else if ((this.potenciaReal > 1450.0F) && (this.potenciaReal <= 1550.0F))
/*     */     {
/*     */       try {
/* 703 */         this.autonomiaBateria = this.xcalcAut1400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException14) {}
/* 706 */       if (tensaoBateriaBruta > 160) {
/* 707 */         this.autonomiaBateria = 8;
/*     */       }
/* 709 */       if (tensaoBateriaBruta < 141) {
/* 710 */         this.autonomiaBateria = 0;
/*     */       }
/* 712 */     } else if ((this.potenciaReal > 1550.0F) && (this.potenciaReal <= 1650.0F))
/*     */     {
/*     */       try {
/* 715 */         this.autonomiaBateria = this.xcalcAut1500[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException15) {}
/* 718 */       if (tensaoBateriaBruta > 160) {
/* 719 */         this.autonomiaBateria = 8;
/*     */       }
/* 721 */       if (tensaoBateriaBruta < 141) {
/* 722 */         this.autonomiaBateria = 0;
/*     */       }
/* 724 */     } else if ((this.potenciaReal > 1650.0F) && (this.potenciaReal <= 1750.0F))
/*     */     {
/*     */       try {
/* 727 */         this.autonomiaBateria = this.xcalcAut1600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException16) {}
/* 730 */       if (tensaoBateriaBruta > 160) {
/* 731 */         this.autonomiaBateria = 8;
/*     */       }
/* 733 */       if (tensaoBateriaBruta < 141) {
/* 734 */         this.autonomiaBateria = 0;
/*     */       }
/* 736 */     } else if ((this.potenciaReal > 1750.0F) && (this.potenciaReal <= 1850.0F))
/*     */     {
/*     */       try {
/* 739 */         this.autonomiaBateria = this.xcalcAut1700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException17) {}
/* 742 */       if (tensaoBateriaBruta > 160) {
/* 743 */         this.autonomiaBateria = 8;
/*     */       }
/* 745 */       if (tensaoBateriaBruta < 141) {
/* 746 */         this.autonomiaBateria = 0;
/*     */       }
/* 748 */     } else if ((this.potenciaReal > 1750.0F) && (this.potenciaReal <= 1850.0F))
/*     */     {
/*     */       try {
/* 751 */         this.autonomiaBateria = this.xcalcAut1800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException18) {}
/* 754 */       if (tensaoBateriaBruta > 160) {
/* 755 */         this.autonomiaBateria = 8;
/*     */       }
/* 757 */       if (tensaoBateriaBruta < 141) {
/* 758 */         this.autonomiaBateria = 0;
/*     */       }
/* 760 */     } else if ((this.potenciaReal > 1850.0F) && (this.potenciaReal <= 1950.0F))
/*     */     {
/*     */       try {
/* 763 */         this.autonomiaBateria = this.xcalcAut1900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException19) {}
/* 766 */       if (tensaoBateriaBruta > 159) {
/* 767 */         this.autonomiaBateria = 7;
/*     */       }
/* 769 */       if (tensaoBateriaBruta < 141) {
/* 770 */         this.autonomiaBateria = 0;
/*     */       }
/* 772 */     } else if ((this.potenciaReal > 1950.0F) && (this.potenciaReal <= 2050.0F))
/*     */     {
/*     */       try {
/* 775 */         this.autonomiaBateria = this.xcalcAut2000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException20) {}
/* 778 */       if (tensaoBateriaBruta > 158) {
/* 779 */         this.autonomiaBateria = 6;
/*     */       }
/* 781 */       if (tensaoBateriaBruta < 141) {
/* 782 */         this.autonomiaBateria = 0;
/*     */       }
/* 784 */     } else if (this.potenciaReal > 2050.0F)
/*     */     {
/*     */       try {
/* 787 */         this.autonomiaBateria = this.xcalcAut2100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException21) {}
/* 790 */       if (tensaoBateriaBruta > 157) {
/* 791 */         this.autonomiaBateria = 5;
/*     */       }
/* 793 */       if (tensaoBateriaBruta < 141) {
/* 794 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 798 */     if (this.expansorBateria > 0)
/* 799 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 18) / 18);
/*     */   }
/*     */   
/*     */   public boolean isTemperaturaAvaliable() {
/* 803 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisM15.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */