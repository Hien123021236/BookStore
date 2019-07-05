drop database QLNhaSach;
go
create database QLNhaSach
go
use QLNhaSach
go


create table Sach
(
	MaSach int primary key identity(10000,1),
	TenSach nvarchar(200),
	TheLoai nvarchar(50),
	TacGia nvarchar(200),
	NhaXuatBan nvarchar(50),
	GiaBan int,
	SoLuongTon int,
	ImgUrl nvarchar(max)
)

create table NhapSach
(
	MaNhap int primary key,
	MaNV int,
	ThoiGian datetime,
	TongTien int default 0,
)

create table ChiTietNhapSach
(	
	MaCTNS int primary key identity(10000,1),
	MaNhap int,
	MaSach int,
	GiaNhap int,
	SoLuong int

)

create table HoaDon 
(
	MaHD int primary key,
	MaKH int,
	ThoiGian datetime,
	TongTien int default 0
)

create table ChiTietHoaDon
(	
	MaCTHD int primary key identity(10000,1),
	MaHD int,
	MaSach int,
	Soluong int,
)

create table KhachHang
(
	MaKH int primary key identity(10000,1),
	MaLoaiKH int,
	TenKH nvarchar(50),
	GioiTinh nvarchar(20),
	DiemTichLuy int
)

create table LoaiKhachHang
(
	MaLoaiKH int primary key identity(10000,1),
	TenLoaiKH nvarchar(50) unique,
	TyLeGiamGia float unique,
	MucDiem int
)


create table NhanVien
(
	MaNV int primary key identity(10000,1),
	Password nvarchar(20),
	ChucVu nvarchar(30),
	TenNV nvarchar(50),
	GioiTinh nvarchar(20),
	NgaySinh date
)


alter table KhachHang add constraint FK_KH_LoaiKH foreign key (MaLoaiKH) references LoaiKhachHang(MaLoaiKH); 

alter table NhapSach add constraint FK_NS_NV foreign key (MaNV) references NhanVien(MaNV); 

alter table ChiTietNhapSach add constraint FK_CTNS_NS foreign key (MaNhap) references NhapSach(MaNhap);

alter table ChiTietNhapSach add constraint FK_CTNS_S foreign key (MaSach) references Sach(MaSach); 

alter table HoaDon add constraint FK_HD_KH foreign key (MaKH) references KhachHang(MaKH); 

alter table ChiTietHoaDon add constraint FK_CTHD_S foreign key (MaSach) references Sach(MaSach);  

alter table ChiTietHoaDon add constraint FK_CTHD_HD foreign key (MaHD) references HoaDon(MaHD);  
 


CREATE trigger tg_insert_ChiTietNhapSach
on ChiTietNhapSach
for insert
as

begin
	declare @MaNhap int;
	declare @MaSach int;
	declare @SoLuong int;
	declare @GiaNhap int;
	declare @TongGia int

	select @MaNhap = MaNhap, @MaSach = MaSach, @SoLuong = SoLuong, @GiaNhap = GiaNhap from inserted;
	set @TongGia = @SoLuong * @GiaNhap
	
	--cap nhat so luong sach
	update Sach 
	set SoLuongTon = SoLuongTon + @SoLuong 
	where MaSach = @MaSach
end



CREATE trigger tg_delete_ChiTietNhapSach
on ChiTietNhapSach
for delete
as

begin
	declare @MaNhap int;
	declare @MaSach int;
	declare @SoLuong int;
	declare @SoLuongTon int;
	declare @GiaNhap int;
	declare @TongGia int

	select @MaNhap = MaNhap, @MaSach = MaSach, @SoLuong = SoLuong, @GiaNhap = GiaNhap from deleted;
	set @TongGia = @SoLuong * @GiaNhap
	
	select @SoLuongTon = SoLuongTon from Sach where MaSach = @MaSach;
	
	if(@SoLuongTon >= @SoLuong)
		begin
			-------------------/*cap nhat so luong sach*/-
			update Sach 
			set SoLuongTon = SoLuongTon - @SoLuong 
			where MaSach = @MaSach
		end
	else
		begin
			rollback;
		end
end


 insert into NhanVien values('123','Manager','Nguyen Van A','Male','21/02/1990');


 insert into Sach values('Tu To Lua Den Silicon','Business','Jeffrey E. Garten','NaN','210000','0','');
 insert into Sach values('Vi Dai Do Lua Chon','Business','Jim Collins - Morten T. Hansen','NaN','100000','0','');
 insert into Sach values('Xay Dung De truong Ton','Business','Jim Collins - Jerry I. Porras','NaN','120000','0','');
 insert into Sach values('Horrible Science - Thien Nhien Hoang Da','Science Technology','Phil Gates, Tony De Saulles','NaN','320000','0','');
 insert into Sach values('Mat Thu','Science Technology','Tran Thoi','NaN','98000','0','');
 insert into Sach values('Lich Su Viet Nam Bang Tranh','History','Tran Bach Dang','NaN','218000','0','');
 insert into Sach values('Than, Nguoi va Dai Viet','History','Ta Chi Dai Truong','NaN','118000','0','');


 set dateformat dmy
 insert into NhapSach(MaNhap, MaNV, ThoiGian) values ('10001','10000','20/2/2019 8:20:22')



 insert into ChiTietNhapSach values ('10000','10000','125000','20');
 insert into ChiTietNhapSach values ('10000','10001','100000','10');
 

 select * from ChiTietNhapSach

 select * from NhapSach

 select * from Sach

 select * from NhanVien

 select MAX(MaNhap) from NhapSach


 update Sach set SoLuongTon = 20 where MaSach = '10000'
 delete ChiTietNhapSach where MaCTNS = 10002


begin
set dateformat dmy
insert into NhapSach(MaNhap, MaNV, ThoiGian) values ('10002','','04-07-2019 21:32:14')
end