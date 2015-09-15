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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SolisM14
/*     */   extends SolisM11
/*     */ {
/*  19 */   protected int[] calcAut0100 = { 1, 1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 8, 10, 11, 13, 15, 18, 22, 24, 28, 34, 40, 43, 48, 56, 65, 70, 76, 85, 93, 100, 110, 122, 132, 141, 152, 162 };
/*     */   
/*  21 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 22, 25, 30, 35, 40, 44, 48, 54, 60, 65, 73, 80, 85, 92, 94, 95 };
/*     */   
/*  23 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 8, 10, 14, 16, 18, 20, 24, 30, 32, 36, 40, 44, 48, 52, 54, 58, 59, 60, 62, 62, 62 };
/*     */   
/*  25 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 8, 9, 10, 12, 13, 15, 17, 20, 22, 24, 28, 32, 35, 38, 42 };
/*     */   
/*  27 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 11, 12, 14, 15, 17, 18, 21, 24, 26, 29, 32, 34, 36 };
/*     */   
/*  29 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 18, 20, 22, 24, 26, 28 };
/*     */   
/*  31 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 11, 13, 14, 15, 17, 19, 21, 22 };
/*     */   
/*  33 */   protected int[] calcAut0800 = { 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 8, 9, 10, 11, 12, 14, 15, 17, 18, 19, 20, 20 };
/*     */   
/*  35 */   protected int[] calcAut0900 = { 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 7, 7, 8, 9, 10, 11, 12, 13, 15, 15, 16, 16, 16 };
/*     */   
/*  37 */   protected int[] calcAut1000 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 7, 8, 9, 9, 10, 11, 12, 12, 12, 13, 13, 14, 14, 14 };
/*     */   
/*  39 */   protected int[] calcAut1100 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 7, 8, 9, 10, 10, 10, 11, 11, 12, 12, 12 };
/*     */   
/*  41 */   protected int[] calcAut1200 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10 };
/*     */   
/*  43 */   protected int[] calcAut1300 = { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM14(ProtocoloUPS protocolo)
/*     */   {
/*  50 */     super(protocolo);
/*  51 */     protocolo.setModeloUPS(14);
/*  52 */     instanciaGlobais();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SolisM14() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/*  65 */     this.potenciaNominalVA = 2000;
/*  66 */     this.potenciaNominalW = 2000;
/*     */     
/*     */ 
/*  69 */     this.frequenciaEntradaC = 101700.0F;
/*     */     
/*  71 */     this.tensaoSaida220F1MB = 3.0F;
/*     */     
/*  73 */     this.tensaoSaida220F2MB = 12.0F;
/*     */     
/*  75 */     this.tensaoSaida220F1MR = 2.93F;
/*     */     
/*  77 */     this.tensaoSaida220F2MR = 13.0F;
/*     */     
/*  79 */     this.tensaoSaida110F1MB = 1.65F;
/*     */     
/*  81 */     this.tensaoSaida110F2MB = 0.0F;
/*     */     
/*  83 */     this.tensaoSaida110F1MR = 1.45F;
/*     */     
/*  85 */     this.tensaoSaida110F2MR = 11.8F;
/*     */     
/*  87 */     this.tensaoEntradaOffset = 30.0F;
/*     */     
/*  89 */     this.comparadorTensaoEntrada = 194;
/*     */     
/*  91 */     this.tensaoEntradaF1193 = 1.141F;
/*     */     
/*  93 */     this.tensaoEntradaF2193 = 13.0F;
/*     */     
/*  95 */     this.tensaoEntradaF1194 = 2.5F;
/*     */     
/*  97 */     this.tensaoEntradaF2194 = -250.0F;
/*     */     
/*  99 */     this.tensaoBateriaF1 = 0.13908206F;
/*     */     
/* 101 */     this.tensaoBateriaF2 = 1.1F;
/*     */     
/* 103 */     this.correnteSaida220F1MB = 0.043103445F;
/*     */     
/* 105 */     this.correnteSaida220F2MB = 0.2F;
/*     */     
/* 107 */     this.correnteSaida220F1MR = 0.043103445F;
/*     */     
/* 109 */     this.correnteSaida220F2MR = 0.2F;
/*     */     
/* 111 */     this.correnteSaida110F1MB = 0.08196721F;
/*     */     
/* 113 */     this.correnteSaida110F2MB = 0.32F;
/*     */     
/* 115 */     this.correnteSaida110F1MR = 0.08196721F;
/*     */     
/* 117 */     this.correnteSaida110F2MR = 0.32F;
/*     */     
/* 119 */     this.potenciaAparenteOffset = 40.0F;
/*     */     
/* 121 */     this.potenciaAparente220F1MR = 0.13245033F;
/*     */     
/* 123 */     this.potenciaAparente220F2MR = 37.0F;
/*     */     
/* 125 */     this.potenciaAparente220F1MB = 0.13793103F;
/*     */     
/* 127 */     this.potenciaAparente220F2MB = 29.0F;
/*     */     
/* 129 */     this.potenciaAparente110F1MR = 0.13422818F;
/*     */     
/* 131 */     this.potenciaAparente110F2MR = 28.0F;
/*     */     
/* 133 */     this.potenciaAparente110F1MB = 0.13793103F;
/*     */     
/* 135 */     this.potenciaAparente110F2MB = 18.2F;
/*     */     
/* 137 */     this.potenciaReal220F1MR = 0.06896552F;
/*     */     
/* 139 */     this.potenciaReal220F2MR = 20.0F;
/*     */     
/* 141 */     this.potenciaReal220F1MB = 0.13986014F;
/*     */     
/* 143 */     this.potenciaReal220F2MB = 30.0F;
/*     */     
/* 145 */     this.potenciaReal110F1MR = 0.14556041F;
/*     */     
/* 147 */     this.potenciaReal110F2MR = 23.0F;
/*     */     
/* 149 */     this.potenciaReal110F1MB = 0.14285715F;
/*     */     
/* 151 */     this.potenciaReal110F2MB = 16.5F;
/*     */     
/* 153 */     this.correnteEntradaF1MR = 35.0F;
/*     */     
/* 155 */     this.correnteEntradaF2MR = 800.0F;
/*     */     
/* 157 */     this.bateriaMaximoMR = 27.0F;
/*     */     
/* 159 */     this.bateriaMinimoMR = 23.0F;
/*     */     
/* 161 */     this.bateriaMaximoMB = 26.0F;
/*     */     
/* 163 */     this.bateriaMinimoMB = 21.0F;
/*     */     
/* 165 */     this.comparadorAutonomiaBateria = 5;
/*     */     
/* 167 */     this.comparadorContadorAutonomiaBaixa = 5;
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
/* 179 */     if (this.potenciaReal <= 20.0F) {
/* 180 */       this.autonomiaBateria = 170;
/* 181 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 184 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 187 */       if (tensaoBateriaBruta > 177) {
/* 188 */         this.autonomiaBateria = 164;
/*     */       }
/* 190 */       if (tensaoBateriaBruta < 141) {
/* 191 */         this.autonomiaBateria = 0;
/*     */       }
/* 193 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 196 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 199 */       if (tensaoBateriaBruta > 177) {
/* 200 */         this.autonomiaBateria = 95;
/*     */       }
/* 202 */       if (tensaoBateriaBruta < 141) {
/* 203 */         this.autonomiaBateria = 0;
/*     */       }
/* 205 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 208 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 211 */       if (tensaoBateriaBruta > 177) {
/* 212 */         this.autonomiaBateria = 64;
/*     */       }
/* 214 */       if (tensaoBateriaBruta < 141) {
/* 215 */         this.autonomiaBateria = 0;
/*     */       }
/* 217 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 220 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 223 */       if (tensaoBateriaBruta > 172) {
/* 224 */         this.autonomiaBateria = 44;
/*     */       }
/* 226 */       if (tensaoBateriaBruta < 141) {
/* 227 */         this.autonomiaBateria = 0;
/*     */       }
/* 229 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 232 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 235 */       if (tensaoBateriaBruta > 170) {
/* 236 */         this.autonomiaBateria = 36;
/*     */       }
/* 238 */       if (tensaoBateriaBruta < 141) {
/* 239 */         this.autonomiaBateria = 0;
/*     */       }
/* 241 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 244 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 247 */       if (tensaoBateriaBruta > 173) {
/* 248 */         this.autonomiaBateria = 28;
/*     */       }
/* 250 */       if (tensaoBateriaBruta < 141) {
/* 251 */         this.autonomiaBateria = 0;
/*     */       }
/* 253 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 256 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 259 */       if (tensaoBateriaBruta > 172) {
/* 260 */         this.autonomiaBateria = 22;
/*     */       }
/* 262 */       if (tensaoBateriaBruta < 141) {
/* 263 */         this.autonomiaBateria = 0;
/*     */       }
/* 265 */     } else if ((this.potenciaReal > 750.0F) && (this.potenciaReal <= 850.0F))
/*     */     {
/*     */       try {
/* 268 */         this.autonomiaBateria = this.calcAut0800[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException7) {}
/* 271 */       if (tensaoBateriaBruta > 172) {
/* 272 */         this.autonomiaBateria = 20;
/*     */       }
/* 274 */       if (tensaoBateriaBruta < 141) {
/* 275 */         this.autonomiaBateria = 0;
/*     */       }
/* 277 */     } else if ((this.potenciaReal > 850.0F) && (this.potenciaReal <= 950.0F))
/*     */     {
/*     */       try {
/* 280 */         this.autonomiaBateria = this.calcAut0900[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException8) {}
/* 283 */       if (tensaoBateriaBruta > 172) {
/* 284 */         this.autonomiaBateria = 16;
/*     */       }
/* 286 */       if (tensaoBateriaBruta < 141) {
/* 287 */         this.autonomiaBateria = 0;
/*     */       }
/* 289 */     } else if ((this.potenciaReal > 950.0F) && (this.potenciaReal <= 1050.0F))
/*     */     {
/*     */       try {
/* 292 */         this.autonomiaBateria = this.calcAut1000[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException9) {}
/* 295 */       if (tensaoBateriaBruta > 172) {
/* 296 */         this.autonomiaBateria = 14;
/*     */       }
/* 298 */       if (tensaoBateriaBruta < 141) {
/* 299 */         this.autonomiaBateria = 0;
/*     */       }
/* 301 */     } else if ((this.potenciaReal > 1050.0F) && (this.potenciaReal <= 1150.0F))
/*     */     {
/*     */       try {
/* 304 */         this.autonomiaBateria = this.calcAut1100[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException10) {}
/* 307 */       if (tensaoBateriaBruta > 172) {
/* 308 */         this.autonomiaBateria = 22;
/*     */       }
/* 310 */       if (tensaoBateriaBruta < 141) {
/* 311 */         this.autonomiaBateria = 0;
/*     */       }
/* 313 */     } else if ((this.potenciaReal > 1150.0F) && (this.potenciaReal <= 1250.0F))
/*     */     {
/*     */       try {
/* 316 */         this.autonomiaBateria = this.calcAut1200[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException11) {}
/* 319 */       if (tensaoBateriaBruta > 172) {
/* 320 */         this.autonomiaBateria = 12;
/*     */       }
/* 322 */       if (tensaoBateriaBruta < 141) {
/* 323 */         this.autonomiaBateria = 0;
/*     */       }
/* 325 */     } else if (this.potenciaReal > 1250.0F)
/*     */     {
/*     */       try {
/* 328 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 141)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException12) {}
/* 331 */       if (tensaoBateriaBruta > 172) {
/* 332 */         this.autonomiaBateria = 8;
/*     */       }
/* 334 */       if (tensaoBateriaBruta < 141) {
/* 335 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 339 */     if (this.expansorBateria > 0) {
/* 340 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\SolisM14.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */