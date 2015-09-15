//
//  This code parses the received packet from the UPS
//  APC No-Break 1200VA/600W - Bivolt 8 Tomadas BZ1200-BR
//
//  Created by Bruno on 14/09/15.
//  Copyright (c) 2015 Bruno Salvador. All rights reserved.
//


/*
 UPS APC Models - Brazil Only
 
 
 The first byte at the received packet is the UPS model:
 (here in this code described by received[0])
 
 SOLIS = 1
 RHINO = 2
 STAY = 3
 SOLIS_LI_700 = 169
 SOLIS_M11 = 171
 SOLIS_M15 = 175
 SOLIS_M14 = 174
 SOLIS_M13 = 173
 SOLISDC_M14 = 201
 SOLISDC_M13 = 206
 SOLISDC_M15 = 207
 CABECALHO_RHINO = 194
 PS800 = 185
 STAY1200_USB = 186
 PS350_CII = 184
 PS2200 = 187
 PS2200_22 = 188
 STAY700_USB = 189
 BZ1500 = 190
 
 In this example the received packet below is related to STAY1200_USB
 
 Received Packed from STAY1200_USB
 BA 6D 88 AE 00 01 A4 93 00 2A 1E 01 00 00 00 00 0E 98 49 60 60 A0 FE
 
 
 
 
 
 
 
 */
#include <stdio.h>



// Global Constants for STAY1200_USB Model
// Stay1200_USB.class

float tensao_entrada_F1 = 1.64;
float tensao_entrada_F2 = 3.34;//9.34;
double tensao_bateria_F1 = 0.1551;
double tensao_bateria_F2 = 0.2525;
float tensao_min_bateria = 20.0;
float tensao_max_bateria = 29.5;
float tensao_flut_bateria = 27.0;

double corrente_saida_F1_MR = 0.12970000389100012;
double corrente_saida_F2_MR = 0.5387060281204546;
double corrente_saida_F1_MI = 0.1372;
double corrente_saida_F2_MI = 0.3456;


double POTENCIAUTIL_DETECCAO_CURVA1_F1[8] = { 0.079, 0.089, 0.0972, 0.0, 0.0805, 0.0883, 0.0, 0.0981 };
double POTENCIAUTIL_DETECCAO_CURVA2_F1[8] = { 0.0763, 0.081, 0.0919, 0.0, 0.0741, 0.0828, 0.0, 0.0938 };
double POTENCIAUTIL_DETECCAO_CURVA3_F1[8] = { 0.0744, 0.0808, 0.0885, 0.0, 0.0732, 0.084, 0.0, 0.0955 };

double POTENCIAUTIL_DETECCAO_CURVA1_F2[8] = { 49.107, 45.449, 48.092, 0.0, 43.633, 47.585, 0.0, 48.831 };
double POTENCIAUTIL_DETECCAO_CURVA2_F2[8] = { 81.732, 94.459, 86.686, 0.0, 84.657, 84.999, 0.0, 78.097 };
double POTENCIAUTIL_DETECCAO_CURVA3_F2[8] = { 122.06, 122.9, 125.75, 0.0, 120.39, 108.52, 0.0, 92.239 };

double POTENCIAUTIL_CURVA1_F1[8] ={ 0.08040007075206226, 0.0894, 0.0999, 0.0, 0.0813, 0.0905, 0.0, 0.1005 };
double POTENCIAUTIL_CURVA2_F1[8] ={ 0.08630063689870031, 0.0946, 0.1068, 0.0, 0.086, 0.0967, 0.0, 0.1088 };
double POTENCIAUTIL_CURVA3_F1[8] = { 0.0896001146881468, 0.0991, 0.1116, 0.0, 0.0967, 0.1068, 0.0, 0.1169 };

double POTENCIAUTIL_CURVA1_F2[8] ={ 45.292, 41.928, 41.727, 0.0, 40.269, 41.81, 0.0, 43.458 };
double POTENCIAUTIL_CURVA2_F2[8] = { 8.3927, 9.2393, 8.2852, 0.0, 8.301, 6.7636, 0.0, 8.2842 };
double POTENCIAUTIL_CURVA3_F2[8] ={ -31.115, -33.777, -33.826, 0.0, -59.513, -57.729, 0.0, -41.333 };

double TENSAO_SAIDA_F1_MR[8] = { 1.1549, 1.0925, 0.0, 0.0, 1.0929, 1.0885, 0.0, 0.8654262224145391 };
double TENSAO_SAIDA_F2_MR[8] = { -6.9157, 11.026, 10.43, 0.0, -0.6109, 12.18, 0.0, 13.677};

double ENSAO_SAIDA_F2_MI[8] ={ 5.59, 9.47, 13.7, 0.0, 0.0, 0.0, 0.0, 0.0 };
double TENSAO_SAIDA_F1_MI[8] = { 7.9, 9.1, 17.6, 0.0, 0.0, 0.0, 0.0, 0.0 };

int autonomia100[18] = { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };


int main(int argc, const char * argv[]) {
  
    

   // Received packet in constant array 'received'
    
    int received[25] = {0xBA, 0x6D,0x88, 0xAE,	0x00, 0x01,0xA4,0x93,0x00,0x2a,0x1e,0x01,	0,	0,	0,	0,	0,	0x0e,0x98,	0x49,0x60,0x60,0xa0,0xfe,-1};

   
    int cabecalhoPacote = received[0];
    int tensaoSaida = received[1] & 0x1;
    
    int tensaoEntrada = received[2];
    float tensaoBateria = received[3] *  tensao_bateria_F1 + tensao_bateria_F2;
    
    int temperaturaUPS = received[4];
    int correnteSaida = received[5];
    int configuracaoRele = received[6];
   
    
    float saida = (float)(corrente_saida_F1_MI * correnteSaida + corrente_saida_F1_MI);
    printf("eita porra saida corrente: %f\n", saida);
    
    int potenciaReal = received[7] + received[8] * 256;
    int segundos = received[9];
    int minutos = received[10];
    int hora = received[11];
    
    int horaLigar = received[13];
    int minutoLigar = received[14];
    
    int horaDesligar = received[15];
    int minutoDesligar = received[16];
    
    int diaSemana = (received[18] & 0xE0) >> 5;
    int diasSemanaProgramados = received[17];
    
    int diaMes = received[18] & 0x1F;
    int ano = (received[19] & 0x0F) + 1998;
    int mes = (received[19] & 0xF0) >> 4;
    
    int tensaoSaida220 = received[20] & 0x01;
    
    
    
    int carregandoBateria = received[20] & 0x2;
    
    int bateriaCritica = received[20] & 0x4; //if == 4 then bateriaCritica
    
    int modoBateria = received[20] & 0x8; //se igual a 8, entao modo bateria
    int modoSuperAquecimento = received[20] & 0x10; //se igual a 16 entao modo de aquecimento
    int modoRede = received[20] & 0x20; //se for diferente de 32, entao modo rede
    
    int tensaoEntrada220 = received[20] & 0x40; //se igual a 64 entoa tensao entrada = 220
    int sobreCarga = received[20] & 0x80; //se igual a 128 entao sobrecarga = true
    
    int modoByPass = received[20] & 0x40; //se igual a 64 entao = true
    
    int frequenciaEntrada1 = (received[21] + (received[22] * 256));
    float frequenciaEntrada = (float)(0.37 * (257 - (frequenciaEntrada1 >> 8)));
    
    int saidaLigada = received[20] & 0x8;
    
    printf("UPS Model: %i\n", cabecalhoPacote);
    printf("tensaoentrda: %i\n", tensaoEntrada);
    if (tensaoSaida220 == 1) {
        //Output Voltage is 220V
        tensaoEntrada = (int)(tensaoEntrada * tensao_entrada_F1 + tensao_entrada_F2);
    } else {
        printf("TENSAO ENTRADA: %i\n", tensaoEntrada);
        tensaoEntrada = (int)(tensao_entrada_F1 * tensaoEntrada + tensao_entrada_F2 - 3.0);
    }
    printf("Tensão de Saida: %i\n", tensaoSaida);
    printf("Tensão de Entrada: %i\n", tensaoEntrada);
    printf("Tensão Bateria: %f\n", tensaoBateria);
    printf("Temperatura UPS: %u\n", temperaturaUPS);
    printf("Corrente saída: %f\n", correnteSaida);
    printf("Estado Rele: %i\n", configuracaoRele );
    printf("Potencia real: %i\n", potenciaReal);
    printf("Hora: %i\n", hora);
    printf("Minutos: %i\n", minutos);
    printf("Segundos: %i\n", segundos);
    printf("Tesao 220: %i. 0 = false\n", tensaoSaida220);
    printf("Bateria Critica: %i\n", bateriaCritica);
    printf("Modo Bateria: %i\n", modoBateria);
    printf("Modo superaquecimento: %i\n", modoSuperAquecimento);
    printf("Modo rede: %i\n", modoRede);
    printf("Carregando bateria: %i\n", carregandoBateria);
    printf("Tensao de Entrada 220. 64=true: %i\n", tensaoEntrada220);
    printf("Sobrecarga 128 = true: %i\n", sobreCarga);
    printf("Frequencia Entrada: %f\n", frequenciaEntrada);
    printf("Saida ligada true == 8: %i\n", saidaLigada);
    
    
    
    
       return 0;
    

}
