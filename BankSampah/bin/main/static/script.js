const jenisSampah = [];
const members = [];
const transaksi = [];
const transaksiBankSampah=[];


function showForm(formId) {
    const form = document.getElementById(formId);
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
}

function hideEditForm() {
    document.getElementById('edit-sampah-form').style.display = 'none';
}

function addJenisSampah(event) {
    event.preventDefault();
    const name = document.getElementById('sampahName').value;
    const jenis = document.getElementById('jenisSampah').value;
    const satuan = document.getElementById('satuan').value;
    const harga = document.getElementById('harga').value;
    const dateInput = document.getElementById('dateInput').value;


    jenisSampah.push({ name, jenis, satuan, harga,dateInput});
    renderJenisSampah();
    document.getElementById('jenis-sampah-form').reset();
}

function editJenisSampah(index) {
    const harga = document.getElementById(`harga-${index}`).innerText;
    const dateInput = document.getElementById(`dateInput-${index}`).innerText;

    document.getElementById(`harga-${index}`).innerHTML = `<input type="number" value="${harga}" id="edit-harga-${index}">`;
    document.getElementById(`dateInput-${index}`).innerHTML = `<input type="date" value="${dateInput}" id="edit-date-${index}">`;

    const button = document.querySelector(`#sampahList tr:nth-child(${index + 1}) button`);
    button.innerText = 'Simpan';
    button.setAttribute('onclick', `saveEdit(${index})`);
}

function saveEdit(index) {
    const harga = document.getElementById(`edit-harga-${index}`).value;
    const dateInput = document.getElementById(`edit-date-${index}`).value;

    document.getElementById(`harga-${index}`).innerText = harga;
    document.getElementById(`dateInput-${index}`).innerText = dateInput;

    const button = document.querySelector(`#sampahList tr:nth-child(${index + 1}) button`);
    button.innerText = 'Edit';
    button.setAttribute('onclick', `editJenisSampah(${index})`);
}


// function deleteJenisSampah(index) {
//     jenisSampah.splice(index, 1);
//     renderJenisSampah();
// } ??

function addMember(event) {
    event.preventDefault();
    const name = document.getElementById('memberName').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const address = document.getElementById('address').value;
    const kelurahan = document.getElementById('Kelurahan').value;

    members.push({ name, email, phone, address, kelurahan });
    renderMembers();
    document.getElementById('member-form').reset();
    showForm('member-form');
}

function addTransaksi(event) {
    event.preventDefault();
    const username= document.getElementById('transaksiMember').value;
    const tanggal = document.getElementById('tanggalTransaksi').value;
    const sampah = document.getElementById('transaksiSampah').value;
    const jumlah = document.getElementById('transaksiJumlah').value;

    transaksi.push({ username, tanggal, sampah,jumlah });
    renderTransaksi();
    document.getElementById('transaksi-form').reset();
    showForm('transaksi-form');
}

    function addTransaksiPenyetoran(event) {
        event.preventDefault();
        const bankSampah= document.getElementById('transaksiBankSampah').value;
        const tanggalz = document.getElementById('tanggalTransaksiPenyetoran').value;
        const sampahz = document.getElementById('transaksiSampahPenyetoran').value;
        const jumlah = document.getElementById('jumlahSampah').value;
        const total = document.getElementById('totalHarga').value;

        transaksiBankSampah.push({ bankSampah, tanggalz, sampahz,jumlah,total});
        renderTransaksiPenyetoran();
        document.getElementById('transaksiPenyetoran-form').reset();
        showForm('transaksiPenyetoran-form');
    }

function showPage(pageId) {
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => {
        page.style.display = 'none';
    });

    const page = document.getElementById(pageId);
    page.style.display = 'block';
}

//render
function renderJenisSampah() {
    const tbody = document.getElementById('sampahList');
    tbody.innerHTML = jenisSampah
        .map((item, index) => `
            <tr>
                <td>${index + 1}</td>
                <td id="name-${index}">${item.name}</td>
                <td id="jenis-${index}">${item.jenis}</td>
                <td id="satuan-${index}">${item.satuan}</td>
                <td id="harga-${index}">${item.harga}</td>
                <td id="date-${index}">${item.dateInput}</td> <!-- Tampilkan tanggal -->
                <td>
                    <button onclick="editJenisSampah(${index})">Edit</button>
                </td>
            </tr>
        `)
        .join('');
}

function renderMembers() {
    const tbody = document.getElementById('memberList');
    tbody.innerHTML = members
        .map((item, index) => `<tr>
            <td>${index + 1}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.phone}</td>
            <td>${item.address}</td>
            <td>${item.kelurahan}</td>

        </tr>`)
        .join('');
}

function renderTransaksi() {
    const tbody = document.getElementById('transaksiList');
    tbody.innerHTML = transaksi
        .map((item, index) => `<tr>
            <td>${index + 1}</td>
            <td>${item.username}</td>
            <td>${item.tanggal}</td>
            <td>${item.sampah}</td>
            <td>${item.jumlah}</td>
        </tr>`)
        .join('');
}

    function renderTransaksiPenyetoran() {
    const tbody = document.getElementById('transaksiPenyetoranList');
    tbody.innerHTML = transaksiBankSampah
        .map((item, index) => `<tr>
            <td>${index + 1}</td>
            <td>${item.bankSampah}</td>
            <td>${item.tanggalz}</td>
            <td>${item.sampahz}</td>
            <td>${item.jumlah}</td>
            <td>${item.total}</td>
        </tr>`)
        .join('');
} 



renderJenisSampah();
renderMembers();
renderTransaksi();
