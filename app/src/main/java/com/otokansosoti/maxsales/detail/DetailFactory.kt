package com.otokansosoti.maxsales.detail
import android.content.Context
import com.otokansosoti.maxsales.R
enum class DetailFactory {
    CONSULTA,
    EXAMES,
    DENTISTA,
    PERSONALFITNESS,
    NUTRICIONAL,
    CHECKUP,
    SORTEIO,
    AUTOMOTO,
    RESIDENCIAL,
    INSPECAO,
    FUNERAL,
    MORTEACIDENTAL,
    PET,
    MEDICAMENTOGRATIS;

    companion object {
        fun fromString(str: String, context: Context): DetailBinding {
            return try {
                valueOf(str.toUpperCase()).make(context)
            } catch (e: IllegalArgumentException) {
                DetailBinding("", "", "", "", "", "", "")
            }
        }
    }

    fun make(context: Context): DetailBinding {
        val model = DetailBinding(
            title = when (this) {
                CONSULTA -> "Consultas"
                EXAMES -> "Exames"
                DENTISTA -> "Dentista"
                PERSONALFITNESS -> "Personal"
                NUTRICIONAL -> "Nutricional"
                CHECKUP -> "Check-Up"
                SORTEIO -> "Sorteio"
                AUTOMOTO -> "Auto & Moto"
                RESIDENCIAL -> "Residencial"
                INSPECAO -> "Inspeção Domiciliar"
                FUNERAL -> "Funeral"
                MORTEACIDENTAL -> "Morte Acidental"
                PET -> "Pet"
                MEDICAMENTOGRATIS -> "Assistência Medicamentos"
            },
            image = when (this) {
                CONSULTA, EXAMES, DENTISTA -> "produto_saude"
                MEDICAMENTOGRATIS -> "logo_sabemi"
                else -> "previsul"
            },
            text = when (this) {
                CONSULTA -> context.getString(R.string.consultaText)
                EXAMES -> context.getString(R.string.examesText)
                DENTISTA -> context.getString(R.string.dentistaText)
                PERSONALFITNESS -> context.getString(R.string.assistenciaPersonalText)
                NUTRICIONAL -> context.getString(R.string.assistenciaNutricionalText)
                CHECKUP -> context.getString(R.string.checkupText)
                SORTEIO -> context.getString(R.string.sorteioText)
                AUTOMOTO -> context.getString(R.string.autoMotoText)
                RESIDENCIAL -> context.getString(R.string.residencialText)
                INSPECAO -> context.getString(R.string.inspecaoText)
                FUNERAL -> context.getString(R.string.funeralText)
                MORTEACIDENTAL -> context.getString(R.string.morteAcidentalText)
                PET -> context.getString(R.string.assistenciaPetText)
                MEDICAMENTOGRATIS -> context.getString(R.string.medicamentoGratisText)
            },
            whatsappLabel = context.getString(R.string.capitais),
            whastappContent = when (this) {
                PERSONALFITNESS, NUTRICIONAL, CHECKUP, SORTEIO, AUTOMOTO, RESIDENCIAL, INSPECAO -> "(11) 3003-6773"
                MEDICAMENTOGRATIS -> ""
                else -> "(11) 97292-2649"
            },
            phoneLabel = context.getString(R.string.outrasLocalidades),
            phoneContent = when (this) {
                MORTEACIDENTAL, SORTEIO, AUTOMOTO, RESIDENCIAL, INSPECAO, FUNERAL -> "0800 709 8059"
                MEDICAMENTOGRATIS -> "0800 721 3874"
                else -> "0800 591 0432"
            }
        )

        return model
    }
}