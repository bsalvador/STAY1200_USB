/*     */ package br.com.schneider.sgm.protocolo;
/*     */ 
/*     */ import br.com.schneider.sgm.comunicacao.Communication;
/*     */ import br.com.schneider.sgm.eventos.Evento;
/*     */ import br.com.schneider.sgm.eventos.ProtocoloListener;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProtocoloPS
/*     */   extends ProtocoloSolis
/*     */ {
/*     */   protected int estadoRele;
/*     */   private int contRefreshTimer;
/*     */   protected static final int SOH_PAC_ACK = 172;
/*     */   protected static final int SOH_PAC_COM = 204;
/*     */   protected static final int COM_LIGAR_SAIDA = 1;
/*     */   protected static final int COM_DESLIGAR_SAIDA = 2;
/*     */   protected static final int COM_LIGAR_ENTRADA = 3;
/*     */   protected static final int COM_DESLIGAR_ENTRADA = 4;
/*     */   protected static final int COM_SHUTDOWN = 9;
/*     */   
/*     */   public boolean ligaEntrada()
/*     */   {
/*  44 */     int[] bytes = new int[4];
/*     */     
/*  46 */     bytes[0] = 204;
/*  47 */     bytes[1] = 3;
/*  48 */     bytes[2] = 3;
/*  49 */     bytes[3] = (2 * bytes[1]);
/*     */     
/*  51 */     return enviaDados(bytes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean desligaEntrada()
/*     */   {
/*  59 */     int[] bytes = new int[4];
/*     */     
/*  61 */     bytes[0] = 204;
/*  62 */     bytes[1] = 4;
/*  63 */     bytes[2] = 4;
/*  64 */     bytes[3] = (2 * bytes[1]);
/*     */     
/*  66 */     return enviaDados(bytes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean ligaSaida()
/*     */   {
/*  76 */     int[] bytes = new int[4];
/*     */     
/*  78 */     bytes[0] = 204;
/*  79 */     bytes[1] = 1;
/*  80 */     bytes[2] = 1;
/*  81 */     bytes[3] = (2 * bytes[1]);
/*  82 */     return enviaDados(bytes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(int bateriaBaixa) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean desligaSaida()
/*     */   {
/* 101 */     int[] bytes = new int[4];
/*     */     
/* 103 */     bytes[0] = 204;
/* 104 */     bytes[1] = 2;
/* 105 */     bytes[2] = 2;
/* 106 */     bytes[3] = (2 * bytes[1]);
/* 107 */     return enviaDados(bytes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setConfiguracaoRele(int conf)
/*     */   {
/* 118 */     this.estadoRele = conf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getConfiguracaoRele()
/*     */   {
/* 128 */     return this.estadoRele;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean enviaDados(int[] pacote)
/*     */   {
/* 137 */     this.comandoAceito = 1;
/*     */     
/* 139 */     this.comunicador.sendBytes(pacote);
/*     */     
/* 141 */     this.esperaACK = false;
/* 142 */     while (this.comandoAceito == 0) {}
/* 143 */     if (this.comandoAceito == 1) {
/* 144 */       this.comandoAceito = 0;
/* 145 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 150 */     this.comandoAceito = 0;
/*     */     
/* 152 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void recebeDados()
/*     */   {
/* 158 */     this.vetorBytes = this.comunicador.getBytes();
/* 159 */     int i = 0;int checksum = 0;
/* 160 */     while (this.vetorBytes[(i++)] != -1) {}
/* 161 */     int numeroBytes = i - 2;
/* 162 */     this.esperaACK = false;
/*     */     
/* 164 */     switch (numeroBytes)
/*     */     {
/*     */     case 1: 
/*     */     case 2: 
/*     */     case 3: 
/*     */       break;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     case 25: 
/* 186 */       if (this.vetorBytes[20] != this.pacoteDados[20]) {
/* 187 */         pedidoDumpping();
/*     */       }
/*     */       
/* 190 */       if (((this.vetorBytes[0] & 0xF0) == 176) && (this.vetorBytes[24] == 254)) {
/* 191 */         for (i = 0; i <= 24; i++) {
/* 192 */           this.pacoteDados[i] = this.vetorBytes[i];
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 197 */         for (i = 0; i <= 22; i++)
/*     */         {
/* 199 */           checksum = this.pacoteDados[i] + checksum;
/*     */         }
/*     */         
/*     */ 
/* 203 */         checksum %= 256;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 209 */         if (this.pacoteDados[23] == checksum)
/*     */         {
/*     */ 
/* 212 */           trataPacoteDados();
/*     */           
/*     */ 
/* 215 */           if (this.contRefreshTimer++ > 260)
/*     */           {
/* 217 */             this.contRefreshTimer = 0;
/* 218 */             Calendar cal = Calendar.getInstance();
/*     */             
/* 220 */             configuraRelogio(cal.get(13), cal.get(12), cal.get(11));
/*     */           }
/*     */           
/* 223 */           checksum = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 231 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     default: 
/* 238 */       if ((this.esperaDumpping) && (this.vetorBytes[0] == 172) && (numeroBytes >= 5)) {
/* 239 */         boolean t = false;
/* 240 */         if ((numeroBytes - 2) % 3 == 0)
/*     */         {
/* 242 */           for (i = 0; i <= numeroBytes - 1; i++) {
/* 243 */             this.pacoteDumpping[i] = this.vetorBytes[i];
/*     */           }
/*     */           
/*     */ 
/* 247 */           for (i = 1; i < numeroBytes - 1; i++) checksum = this.pacoteDumpping[i] + checksum;
/* 248 */           checksum %= 256;
/*     */           
/* 250 */           if (this.pacoteDumpping[(numeroBytes - 1)] == checksum) {
/* 251 */             int numeroEventos = (numeroBytes - 2) / 3;
/* 252 */             trataPacoteDumpping(numeroEventos);
/* 253 */             checksum = 0;
/*     */           }
/*     */           else {
/* 256 */             t = true;
/*     */           }
/*     */         } else {
/* 259 */           t = true; }
/* 260 */         if (t) {
/* 261 */           int cont = 0;
/*     */           
/* 263 */           for (i = 1; i < numeroBytes - 1; i++) {
/* 264 */             checksum = this.vetorBytes[i] + checksum;
/* 265 */             cont++;
/* 266 */             if (cont >= 3) {
/* 267 */               cont = 0;
/* 268 */               if ((this.vetorBytes[(i + 1)] == checksum % 256) && ((this.modeloUPS == (this.vetorBytes[(i + 2)] & 0xF)) || (this.vetorBytes[(i + 2)] == 172) || 
/* 269 */                 (this.vetorBytes[(i + 2)] == 238))) {
/* 270 */                 for (int k = 0; k <= i + 1; k++) this.pacoteDumpping[k] = this.vetorBytes[k];
/* 271 */                 int numeroEventos = i / 3;
/* 272 */                 trataPacoteDumpping(numeroEventos);
/* 273 */                 checksum = 0;
/* 274 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 280 */       else if ((this.esperaACK) && (this.vetorBytes[0] == 172)) {
/* 281 */         this.esperaACK = false;
/*     */       }
/*     */       
/*     */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean setModoContinuo()
/*     */   {
/* 292 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trataPacoteDumpping(int numeroEventos)
/*     */   {
/* 303 */     Calendar calendar = new GregorianCalendar();
/*     */     
/*     */ 
/*     */ 
/* 307 */     for (int i = 0; i < numeroEventos; i++)
/*     */     {
/* 309 */       int hora = (this.pacoteDumpping[(i * 3 + 1)] & 0xF8) >> 3;
/* 310 */       int minuto = (this.pacoteDumpping[(i * 3 + 1)] & 0x7) << 3 | (this.pacoteDumpping[(i * 3 + 2)] & 0xE0) >> 5;
/* 311 */       int segundo = calendar.get(13);
/* 312 */       int tipo = this.pacoteDumpping[(i * 3 + 2)] & 0x1F;
/* 313 */       int offSet = this.pacoteDumpping[(i * 3 + 3)] & 0x7F;
/*     */       
/* 315 */       int mesAtual = calendar.get(2) + 1;
/* 316 */       int anoAtual = calendar.get(1);
/*     */       
/* 318 */       if ((hora <= 23) && (minuto <= 59) && (getDiaMes() + offSet <= 31)) {
/* 319 */         this.evento = new Evento(tipo, hora, minuto, segundo, getDiaMes() + offSet, mesAtual, anoAtual);
/*     */         
/* 321 */         addEvento(this.evento);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 327 */     if ((this.pacoteDumpping[(i * 3 + 3)] & 0x80) == 1) {
/* 328 */       this.esperaDumpping = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addEvento(Evento e)
/*     */   {
/* 338 */     this.alarmesMicrosol[this.numeroAlarmesMicrosol] = e;
/* 339 */     this.numeroAlarmesMicrosol += 1;
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
/*     */   protected void trataPacoteDados()
/*     */   {
/* 352 */     setCabecalhoPacote(this.pacoteDados[0]);
/* 353 */     setTensaoSaida(this.pacoteDados[1]);
/*     */     
/* 355 */     setTensaoEntrada(this.pacoteDados[2]);
/*     */     
/* 357 */     setTensaoBateria(this.pacoteDados[3]);
/*     */     
/* 359 */     setTemperaturaUPS(this.pacoteDados[4]);
/* 360 */     setCorrenteSaida(this.pacoteDados[5]);
/*     */     
/* 362 */     setConfiguracaoRele(this.pacoteDados[6]);
/* 363 */     setPotenciaReal(this.pacoteDados[7], this.pacoteDados[8]);
/*     */     
/* 365 */     setSegundos(this.pacoteDados[9]);
/* 366 */     setMinutos(this.pacoteDados[10]);
/* 367 */     setHora(this.pacoteDados[11]);
/*     */     
/* 369 */     setHoraLigar(this.pacoteDados[13]);
/* 370 */     setMinutoLigar(this.pacoteDados[14]);
/* 371 */     setHoraDesligar(this.pacoteDados[15]);
/* 372 */     setMinutoDesligar(this.pacoteDados[16]);
/* 373 */     setDiaSemana(this.pacoteDados[18]);
/* 374 */     setDiasSemanaProgramados(this.pacoteDados[17]);
/* 375 */     setDiaMes(this.pacoteDados[18]);
/* 376 */     setAno(this.pacoteDados[19]);
/* 377 */     setMes(this.pacoteDados[19]);
/* 378 */     setTensaoSaida220(this.pacoteDados[20]);
/*     */     
/* 380 */     setBateriaCritica(this.pacoteDados[20]);
/* 381 */     setModoBateria(this.pacoteDados[20]);
/* 382 */     setSuperAquecimento(this.pacoteDados[20]);
/* 383 */     setModoRede(this.pacoteDados[20]);
/* 384 */     setCarregandoBateria(this.pacoteDados[20]);
/* 385 */     setTensaoEntrada220(this.pacoteDados[20]);
/* 386 */     setSobrecarga(this.pacoteDados[20]);
/* 387 */     setFrequenciaEntrada(this.pacoteDados[21], this.pacoteDados[22]);
/* 388 */     setSaidaLigada(this.pacoteDados[20]);
/*     */     
/* 390 */     if (this.ouvinteProtocolo != null) {
/* 391 */       this.ouvinteProtocolo.notifica();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntrada220(int tensaoEntrada220)
/*     */   {
/* 400 */     this.tensaoEntrada220 = ((tensaoEntrada220 & 0x40) == 64);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSaidaLigada(int saidaLigada)
/*     */   {
/* 408 */     this.saidaLigada = ((saidaLigada & 0x8) == 8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addProtocoloListener(ProtocoloListener ouvinteProtocolo)
/*     */   {
/* 417 */     this.ouvinteProtocolo = ouvinteProtocolo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean shutdown()
/*     */   {
/* 425 */     int[] bytes = new int[4];
/*     */     
/* 427 */     bytes[0] = 204;
/* 428 */     bytes[1] = 9;
/* 429 */     bytes[2] = 9;
/* 430 */     bytes[3] = (2 * bytes[1]);
/* 431 */     return enviaDados(bytes);
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\protocolo\ProtocoloPS.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */