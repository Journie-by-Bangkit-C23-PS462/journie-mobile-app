package com.dicoding.journie.data

data class Destination(
    val name: String,
    val subname: String,
    val description: String,
    val urlImage: String
)

val dummyDestination = listOf(
    Destination(
        "Jakarta",
        "DKI Jakarta",
        "Ini kota Jakarta",
        "https://th.bing.com/th/id/R.e70e26ad920880ca0e7d9afceff7cee6?rik=U2WVPDmd%2bOM2Sg&riu=http%3a%2f%2fblog.reservasi.com%2fwp-content%2fuploads%2f2015%2f08%2fTidak-Liburan-Ke-Luar-Kota-Ide-Staycation-Dan-Liburan-Di-Jakarta-Ini-Akan-Sangat-Membantu-Kalian.jpg&ehk=eB%2bSd1G7jxyo1pXhvaVb1VoB%2blKyktQ5rrc53mlXMVU%3d&risl=&pid=ImgRaw&r=0"
    ),
    Destination(
        "Bandung",
        "Jawa Barat",
        "Ini kota Bandung",
        "https://th.bing.com/th/id/R.8e0fb11dc04dd053d6cdbcd52dc9bcc6?rik=iD%2foSogxC9oC2g&riu=http%3a%2f%2f2.bp.blogspot.com%2f-DLWPx3Ldj_A%2fVcTOMJ6iwjI%2fAAAAAAAABUY%2fh9c_lfakEos%2fs1600%2fsate.jpg&ehk=5ImaTipkL%2fcjJK%2fTtXOQ4SIHtRIGRk8hU89wPbchHqQ%3d&risl=&pid=ImgRaw&r=0"
    ),
    Destination(
        "Semarang",
        "Jawa Tengah",
        "Ini Kota Semarang",
        "https://th.bing.com/th/id/R.69fed3ef6116c66422a4ff6d27f5bdd0?rik=F0YhO%2fgpwNK2Vw&riu=http%3a%2f%2f1.bp.blogspot.com%2f-YBS0Te_L27A%2fUWltwNzfPwI%2fAAAAAAAAAHM%2fTYyORcaCO84%2fs1600%2fMonumen%2bSemarang.jpg&ehk=WBnStMOQypdlDVvi27giChrzqnru7liXv3W0jjOdm4w%3d&risl=&pid=ImgRaw&r=0"
    ),
    Destination(
        "Surabaya",
        "Jawa Timur",
        "Ini kota Surabaya",
        "https://th.bing.com/th/id/OIP.BzUA9stjk0d3FSzP3jIPLwHaEb?pid=ImgDet&rs=1"
    )
)