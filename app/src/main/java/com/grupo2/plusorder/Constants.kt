package com.grupo2.plusorder

import java.util.*

object Constants {

    public val EMPTY_GUID : UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
    public val DEFAULT_CIDADE : UUID = UUID.fromString("78357FA1-D5C9-4622-94E9-5293113F7097")
    public val DEFAULT_IMAGEM : String = "https://www.tenforums.com/geek/gars/images/2/types/thumb_15951118880user.png"
    public val DEFAULT_FUNCIONARIO: UUID = UUID.fromString("27DFE5A9-ED9F-40B7-AC5B-474FA23D74EF")

    public var idCliente : UUID? = null
    public var idPrato : UUID? = null
    public var idMesa : UUID? = null

}