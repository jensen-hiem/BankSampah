const jenisSampah = [];
const members = [];
const transaksi = [];


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
    const name = document.getElementById(`name-${index}`).innerText;
    const jenis = document.getElementById(`jenis-${index}`).innerText;
    const satuan = document.getElementById(`satuan-${index}`).innerText;
    const harga = document.getElementById(`harga-${index}`).innerText;

    document.getElementById(`name-${index}`).innerHTML = `<input type="text" value="${name}" id="edit-name-${index}">`;
    document.getElementById(`jenis-${index}`).innerHTML = `<input type="text" value="${jenis}" id="edit-name-${index}">`;
    document.getElementById(`satuan-${index}`).innerHTML = `<input type="text" value="${satuan}" id="edit-satuan-${index}">`;
    document.getElementById(`harga-${index}`).innerHTML = `<input type="number" value="${harga}" id="edit-harga-${index}">`;

    const button = document.querySelector(`#sampahList tr:nth-child(${index + 1}) button`);
    button.innerText = 'Simpan';
    button.setAttribute('onclick', `saveEdit(${index})`);
}

function saveEdit(index) {
    const name = document.getElementById(`edit-name-${index}`).value;
    const jenis = document.getElementById(`edit-name-${index}`).value;
    const satuan = document.getElementById(`edit-satuan-${index}`).value;
    const harga = document.getElementById(`edit-harga-${index}`).value;

    jenisSampah[index] = { name, jenis, satuan, harga };

    document.getElementById(`name-${index}`).innerText = name;
    document.getElementById(`jenis-${index}`).innerText = jenis;
    document.getElementById(`satuan-${index}`).innerText = satuan;
    document.getElementById(`harga-${index}`).innerText = harga;

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
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const address = document.getElementById('address').value;

    members.push({ name, phone, email, address });
    renderMembers();
    document.getElementById('member-form').reset();
    showForm('member-form');
}

function addTransaksi(event) {
    event.preventDefault();
    const member = document.getElementById('transaksiMember').value;
    const sampah = document.getElementById('transaksiSampah').value;
    const jumlah = document.getElementById('transaksiJumlah').value;

    transaksi.push({ member, sampah, jumlah });
    renderTransaksi();
    document.getElementById('transaksi-form').reset();
    showForm('transaksi-form');
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
            <td>${item.phone}</td>
            <td>${item.email}</td>
            <td>${item.address}</td>
        </tr>`)
        .join('');
}

function renderTransaksi() {
    const tbody = document.getElementById('transaksiList');
    tbody.innerHTML = transaksi
        .map((item, index) => `<tr>
            <td>${index + 1}</td>
            <td>${item.member}</td>
            <td>${item.sampah}</td>
            <td>${item.jumlah}</td>
        </tr>`)
        .join('');
}

renderJenisSampah();
renderMembers();
renderTransaksi();
